package com.koa.coremodule.fcm.application.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.fcm.application.dto.AlarmLists;
import com.koa.coremodule.fcm.domain.entity.Alarm;
import com.koa.coremodule.fcm.domain.entity.AlarmType;
import com.koa.coremodule.fcm.domain.entity.AlarmView;
import com.koa.coremodule.fcm.domain.service.AlarmQueryService;
import com.koa.coremodule.fcm.domain.service.AlarmSaveService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.fcm.SendCommentNotificationRequest;
import com.koa.coremodule.notice.application.dto.fcm.SendNoticeNotificationRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AlarmUseCase {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberUtils memberUtils;
    private final AlarmSaveService alarmSaveService;
    private final AlarmQueryService alarmQueryService;
    private final NoticeQueryService noticeQueryService;
    private final CommentQueryService commentQueryService;

    private final static String NOTICE_TITLE = "새로운 공지를 확인하세요";
    private final static String COMMENT_TITLE = "새로운 댓글이 달렸어요";

    public void registerFcmToken(String token) {

        Member memberRequest = memberUtils.getAccessMember();

        Member member = findMember(memberRequest.getId());
        member.updateFcmToken(token);
        log.info("회원의 fcm 토큰이 등록되었습니다.");
    }

    public void sendNoticeNotification(SendNoticeNotificationRequest request) {

        List<Member> members = findAllMember();

        Notification notification = Notification.builder()
                .setTitle(NOTICE_TITLE)
                .setBody(request.content())
                .build();

        for (Member m : members) {
            if (m.getFcmToken() != null && !m.getFcmToken().isEmpty()) {
                Message message = Message.builder()
                        .setToken(m.getFcmToken())
                        .setNotification(notification)
                        .build();

                try {
                    firebaseMessaging.send(message);

                    Notice notice = noticeQueryService.findByNoticeId(request.noticeId());
                    // 알람 테이블 저장
                    Alarm alarm = Alarm.builder()
                            .type(AlarmType.NOTICE)
                            .title(NOTICE_TITLE)
                            .content(request.content())
                            .notice(notice)
                            .build();
                    alarmSaveService.save(alarm);

                } catch (FirebaseMessagingException ex) {
                    log.info("알림 전송에 실패했습니다.");
                }
            }
        }
    }

    public void sendCommentNotification(SendCommentNotificationRequest request) {

        Comment comment = commentQueryService.getCommentById(request.commentId());
        Notice noticeInfo = comment.getNotice();
        Member member = findNoticeMember(noticeInfo.getId());

        Notification notification = Notification.builder()
                .setTitle(COMMENT_TITLE)
                .setBody(request.content())
                .build();

        if (member.getFcmToken() != null && !member.getFcmToken().isEmpty()) {
            Message message = Message.builder()
                    .setToken(member.getFcmToken())
                    .setNotification(notification)
                    .build();

            try {
                firebaseMessaging.send(message);

                // 알람 테이블 저장
                Alarm alarm = Alarm.builder()
                        .type(AlarmType.COMMENT)
                        .title(COMMENT_TITLE)
                        .content(request.content())
                        .notice(noticeInfo)
                        .comment(comment)
                        .build();
                alarmSaveService.save(alarm);

            } catch (FirebaseMessagingException ex) {
                log.info("알림 전송에 실패했습니다.");
            }
        }
    }

    public void sendReCommentNotification(SendCommentNotificationRequest request) {

        List<Member> members = new ArrayList<>();

        Comment comment = commentQueryService.getCommentById(request.commentId());
        Notice noticeInfo = comment.getNotice();
        Member noticeMember = findNoticeMember(noticeInfo.getId());

        Comment parentComment = commentQueryService.getCommentById(comment.getParentId());
        Member commentMember = findCommentMember(parentComment.getId());
        members.add(noticeMember);
        members.add(commentMember);

        Notification notification = Notification.builder()
                .setTitle(COMMENT_TITLE)
                .setBody(request.content())
                .build();

        for (Member m : members) {
            if (m.getFcmToken() != null && !m.getFcmToken().isEmpty()) {
                Message message = Message.builder()
                        .setToken(m.getFcmToken())
                        .setNotification(notification)
                        .build();

                try {
                    firebaseMessaging.send(message);

                    // 알람 테이블 저장
                    Alarm alarm = Alarm.builder()
                            .type(AlarmType.RECOMMENT)
                            .title(COMMENT_TITLE)
                            .content(request.content())
                            .notice(noticeInfo)
                            .comment(comment)
                            .build();
                    alarmSaveService.save(alarm);

                } catch (FirebaseMessagingException ex) {
                    log.info("알림 전송에 실패했습니다.");
                }
            }
        }
    }

    public List<AlarmLists> getAlarmLists() {

        Member memberRequest = memberUtils.getAccessMember();
        List<Alarm> alarmList = alarmQueryService.findAll();
        List<AlarmView> alarmViews = alarmQueryService.findViews(memberRequest.getId());
        List<AlarmLists> result = new ArrayList<>();

        for (Alarm a : alarmList) {
            boolean isViewed = false;

            // 조회 여부 확인
            for (AlarmView al : alarmViews) {
                if (a.getId().equals(al.getId())) {
                    isViewed = true;
                    break;
                }
            }

            AlarmLists alarmLists = AlarmLists.builder()
                    .alarmId(a.getId())
                    .title(a.getTitle())
                    .content(a.getContent())
                    .date(a.getCreatedAt())
                    .viewYn(isViewed)  // viewYn을 조회 여부에 따라 설정
                    .build();

            if (a.getNotice() != null) {
                alarmLists.setNoticeId(a.getNotice().getId());
            }
            if (a.getComment() != null) {
                alarmLists.setCommentId(a.getComment().getId());
            }

            result.add(alarmLists);
        }

        return result;
    }

    public void saveAlarmView(Long alarmId) {

        Member memberRequest = memberUtils.getAccessMember();
        Member member = alarmQueryService.findMember(memberRequest.getId());
        Alarm alarm = alarmQueryService.findAlarmById(alarmId);

        AlarmView alarmView = AlarmView.builder().alarm(alarm).view(ViewType.VIEWED).member(member).build();
        alarmSaveService.saveAlarmView(alarmView);
    }

    public void deleteFcmToken() {

        Member memberRequest = memberUtils.getAccessMember();
        alarmSaveService.deleteFcmToken(memberRequest.getId());
    }

    public void deleteAlarm(Long alarmId) {
        alarmSaveService.deleteAlarm(alarmId);
    }

    private Member findMember(Long memberId) {
        return alarmQueryService.findMember(memberId);
    }

    private List<Member> findAllMember() {
        return alarmQueryService.findAllMember();
    }

    private Member findNoticeMember(Long noticeId) {
        return alarmQueryService.findNoticeMember(noticeId);
    }

    private Member findCommentMember(Long memberId) {
        return alarmQueryService.findMember(memberId);
    }
}
