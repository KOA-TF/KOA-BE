package com.koa.coremodule.email.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.email.application.dto.request.VerifyCodeRequest;
import com.koa.coremodule.email.application.dto.request.VerifyEmailRequest;
import com.koa.coremodule.email.service.EmailSendService;
import com.koa.coremodule.email.service.EmailVerifyService;
import com.koa.coremodule.email.application.dto.response.VerifyCodeResponse;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class EmailVerificationUseCase {

    private final EmailSendService emailSendService;
    private final EmailVerifyService emailVerifyService;
    public void sendVerificationEmail(VerifyEmailRequest verifyEmailRequest) {
        emailSendService.sendEmail(verifyEmailRequest.getEmail());
    }

    public VerifyCodeResponse verifyCode(VerifyCodeRequest verifyCodeRequest) {
        final boolean isVerified = emailVerifyService.verifyCode(verifyCodeRequest.getEmail(), verifyCodeRequest.getCode());
        return new VerifyCodeResponse(isVerified);
    }
}
