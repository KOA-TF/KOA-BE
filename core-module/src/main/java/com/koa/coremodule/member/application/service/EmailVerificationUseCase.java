package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.email.service.EmailSendService;
import com.koa.coremodule.email.service.EmailVerifyService;
import com.koa.coremodule.member.application.dto.response.VerifyCodeResponse;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class EmailVerificationUseCase {

    private final EmailSendService emailSendService;
    private final EmailVerifyService emailVerifyService;
    private final MemberQueryService memberQueryService;

    public void sendVerificationEmail(String email) {
        emailSendService.sendEmail(email);
    }

    public VerifyCodeResponse verifyCode(String email, String code) {
        final boolean isVerified = emailVerifyService.verifyCode(email, code);
        return new VerifyCodeResponse(isVerified);
    }
}
