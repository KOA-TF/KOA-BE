package com.koa.coremodule.notice.application.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeFcmUseCase {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberRepository memberRepository;
    private final MemberUtils memberUtils;

    @Transactional
    public void registerFcmToken(String token) {

        Member memberRequest = memberUtils.getAccessMember();

        Member member = findMember(memberRequest.getId());
        member.updateFcmToken(token);
        log.info("회원의 fcm 토큰이 등록되었습니다.");
    }

    @Transactional(readOnly = true)
    public void sendNotification(String title, String content) {

        Member memberRequest = memberUtils.getAccessMember();

        Member member = findMember(memberRequest.getId());
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(content)
                .build();
        Message message = Message.builder()
                .setToken(member.getFcmToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException ex) {
            log.info("알림 전송에 실패했습니다.");
        }
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(Error.MEMBER_NOT_FOUND));
    }
}
