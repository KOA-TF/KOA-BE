package com.koa.coremodule.fcm.application.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.attend.domain.exception.AttendException;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
    private static final DateTimeFormatter LAST_DATE_FORMATTER = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
    private static final DateTimeFormatter NOW_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd HH:mm");

    public void registerFcmToken(String token) {

        Member memberRequest = memberUtils.getAccessMember();

        Member member = findMember(memberRequest.getId());
        member.updateFcmToken(token);
        log.info("회원의 fcm 토큰이 등록되었습니다.");
    }

    public void sendNoticeNotification(SendNoticeNotificationRequest request) {

        Member memberRequest = memberUtils.getAccessMember();
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
                            .member(memberRequest)
                            .build();
                    alarmSaveService.save(alarm);

                } catch (FirebaseMessagingException ex) {
                    log.info("알림 전송에 실패했습니다.");
                }
            }
        }
    }

    public void sendCommentNotification(SendCommentNotificationRequest request) {

        Member memberRequest = memberUtils.getAccessMember();

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
                        .member(memberRequest)
                        .build();
                alarmSaveService.save(alarm);

            } catch (FirebaseMessagingException ex) {
                log.info("알림 전송에 실패했습니다.");
            }
        }
    }

    public void sendReCommentNotification(SendCommentNotificationRequest request) {

        Member memberRequest = memberUtils.getAccessMember();
        List<Member> members = new ArrayList<>();

        Comment comment = commentQueryService.getCommentById(request.commentId());
        Member noticeMember = findNoticeMember(comment.getNotice().getId());

        Comment parentComment = commentQueryService.getCommentById(comment.getParentId());
        Member commentMember = parentComment.getWriter();


        if (noticeMember.getId().equals(commentMember.getId())) {
            members.add(noticeMember);
        } else {
            members.add(noticeMember);
            members.add(commentMember);
        }
        
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
                            .notice(comment.getNotice())
                            .comment(comment)
                            .member(memberRequest)
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
        List<Alarm> filteredAlarmList = alarmList.stream()
                .filter(alarm -> !alarm.getMember().getId().equals(memberRequest.getId()))
                .sorted(Comparator.comparing(Alarm::getCreatedAt).reversed())
                .toList();

        List<AlarmView> alarmViews = alarmQueryService.findViews(memberRequest.getId());
        List<AlarmLists> result = new ArrayList<>();

        for (Alarm a : filteredAlarmList) {
            boolean isViewed = false;

            // 조회 여부 확인
            for (AlarmView al : alarmViews) {
                if (a.getId().equals(al.getAlarm().getId())) {
                    isViewed = true;
                    break;
                }
            }

            DateTimeFormatter selectedFormatter = LocalDate.now().getYear() == a.getCreatedAt().getYear()
                    ? NOW_DATE_FORMATTER
                    : LAST_DATE_FORMATTER;

            AlarmLists alarmLists = AlarmLists.builder()
                    .alarmId(a.getId())
                    .title(a.getTitle())
                    .content(a.getContent())
                    .date(a.getCreatedAt().format(selectedFormatter))
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

        //이미 적재 되어있으면 예외처리
        if (alarmQueryService.existsByAlarmIdAndMemberId(alarm.getId(), member.getId())) {
            throw new AttendException(Error.DUPLICATE_ALARM);
        }

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
