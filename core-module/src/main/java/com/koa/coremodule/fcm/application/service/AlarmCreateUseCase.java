package com.koa.coremodule.fcm.application.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.service.CommentQueryService;
import com.koa.coremodule.fcm.domain.entity.Alarm;
import com.koa.coremodule.fcm.domain.entity.AlarmType;
import com.koa.coremodule.fcm.domain.entity.AlarmView;
import com.koa.coremodule.fcm.domain.service.AlarmQueryService;
import com.koa.coremodule.fcm.domain.service.AlarmSaveService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.fcm.SendCommentNotificationRequest;
import com.koa.coremodule.notice.application.dto.fcm.SendNoticeNotificationRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationService
@RequiredArgsConstructor
@Transactional
public class AlarmCreateUseCase {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberUtils memberUtils;
    private final AlarmSaveService alarmSaveService;
    private final AlarmQueryService alarmQueryService;
    private final NoticeQueryService noticeQueryService;
    private final CommentQueryService commentQueryService;
    private final MemberQueryService memberQueryService;

    private final static String NOTICE_TITLE = "새로운 공지를 확인하세요";
    private final static String COMMENT_TITLE = "새로운 댓글이 달렸어요";

    public void registerFcmToken(String token) {

        Member memberRequest = memberUtils.getAccessMember();
        memberRequest.updateFcmToken(token);
        log.info("회원의 fcm 토큰이 등록되었습니다.");

    }

    public void sendNoticeNotification(SendNoticeNotificationRequest request) {

        Member memberRequest = memberUtils.getAccessMember();
        List<Member> members = memberQueryService.findAllMember();

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

        Comment comment = commentQueryService.getCommentByIdWithNoticeAndMember(request.commentId());
        Notice noticeInfo = comment.getNotice();
        Member member = noticeInfo.getMember();

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

        Comment comment = commentQueryService.getCommentByIdWithNoticeAndMember(request.commentId());
        Member noticeMember = comment.getNotice().getMember();

        Comment parentComment = commentQueryService.getCommentByIdWithWriter(comment.getParentId());
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

    public void saveAlarmView(Long alarmId) {

        Member memberRequest = memberUtils.getAccessMember();
        Alarm alarm = alarmQueryService.findAlarmById(alarmId);

        //이미 적재 되어있으면 예외처리
        alarmQueryService.existsByAlarmIdAndMemberId(alarm.getId(), memberRequest.getId());

        AlarmView alarmView = AlarmView.builder().alarm(alarm).view(ViewType.VIEWED).member(memberRequest).build();
        alarmSaveService.saveAlarmView(alarmView);

    }


}