package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.application.dto.response.VerifyCodeResponse;
import com.koa.coremodule.member.application.service.EmailVerificationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class EmailController {
    private final EmailVerificationUseCase emailVerificationUseCase;

    @PostMapping("/verify")
    public ApplicationResponse<Void> postVerifyEmail(@RequestParam String email) {
        emailVerificationUseCase.sendVerificationEmail(email);
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/verify/code")
    public ApplicationResponse<VerifyCodeResponse> verifyCode(@RequestParam String email, @RequestParam String code) {
        VerifyCodeResponse response = emailVerificationUseCase.verifyCode(email, code);
        return ApplicationResponse.ok(response);
    }
}
