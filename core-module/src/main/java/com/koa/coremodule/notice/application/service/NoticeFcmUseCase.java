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
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeFcmUseCase {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberUtils memberUtils;
    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;

    @Transactional
    public void registerFcmToken(String token) {

        Member memberRequest = memberUtils.getAccessMember();

        Member member = findMember(memberRequest.getId());
        member.updateFcmToken(token);
        log.info("회원의 fcm 토큰이 등록되었습니다.");
    }

    @Transactional
    public void sendNotification(String title, String content) {

        List<Member> members = findAllMember();

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(content)
                .build();

        for (Member m : members) {
            if (m.getFcmToken() != null && !m.getFcmToken().isEmpty()) {
                Message message = Message.builder()
                        .setToken(m.getFcmToken())
                        .setNotification(notification)
                        .build();

                try {
                    firebaseMessaging.send(message);
                } catch (FirebaseMessagingException ex) {
                    log.info("알림 전송에 실패했습니다.");
                }
            }
        }
    }

    @Transactional
    public void deleteFcmToken() {

        Member memberRequest = memberUtils.getAccessMember();

        noticeRepository.deleteToken(memberRequest.getId());
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(Error.MEMBER_NOT_FOUND));
    }

    private List<Member> findAllMember() {
        return memberRepository.findAll();
    }
}
