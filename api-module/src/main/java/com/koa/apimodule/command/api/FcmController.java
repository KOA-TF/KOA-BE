package com.koa.apimodule.command.api;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.fcm.RegisterTokenRequest;
import com.koa.coremodule.notice.application.dto.fcm.SendNotificationRequest;
import com.koa.coremodule.notice.application.service.NoticeFcmUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/fcm")
public class FcmController {

    private final NoticeFcmUseCase noticeFcmUseCase;
    private final MemberUtils memberUtils;

    @PostMapping("/register")
    public ResponseEntity<String> registerToken(@RequestBody RegisterTokenRequest request) {

        Member memberRequest = memberUtils.getAccessMember();
        noticeFcmUseCase.registerFcmToken(memberRequest.getId(), request.fcmToken());

        return ResponseEntity.ok("성공적으로 토큰이 등록되었습니다.");
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody SendNotificationRequest request) {

        Member memberRequest = memberUtils.getAccessMember();
        noticeFcmUseCase.sendNotification(memberRequest.getId(), request.title(), request.content());

        return ResponseEntity.ok("성공적으로 알림을 보냈습니다.");
    }

}
