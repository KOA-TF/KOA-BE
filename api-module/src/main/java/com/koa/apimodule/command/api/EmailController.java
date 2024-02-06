package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.email.application.dto.request.VerifyCodeRequest;
import com.koa.coremodule.email.application.dto.request.VerifyEmailRequest;
import com.koa.coremodule.email.application.dto.response.VerifyCodeResponse;
import com.koa.coremodule.email.application.service.EmailVerificationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class EmailController {
    private final EmailVerificationUseCase emailVerificationUseCase;

    @PostMapping("/verify")
    public ApplicationResponse<Void> postVerifyEmail(@RequestBody VerifyEmailRequest verifyEmailRequest) {
        emailVerificationUseCase.sendVerificationEmail(verifyEmailRequest);
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/verify/code")
    public ApplicationResponse<VerifyCodeResponse> verifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        VerifyCodeResponse response = emailVerificationUseCase.verifyCode(verifyCodeRequest);
        return ApplicationResponse.ok(response);
    }
}
