package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.service.AuthUseCase;
import com.koa.coremodule.auth.application.service.LogoutUseCase;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.notice.application.service.NoticeFcmUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {


    private final AuthUseCase authUseCase;
    private final LogoutUseCase logoutUseCase;
    private final NoticeFcmUseCase noticeFcmUseCase;

    @GetMapping("/login/{authority}")
    public ApplicationResponse<AuthResponse> authLogin(@PathVariable Authority authority, @RequestParam String email, @RequestParam String password){
        AuthResponse response = authUseCase.authLogin(authority, email, password);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/reissue")
    public ApplicationResponse<AuthResponse> authReissue(@RequestHeader(AuthConsts.REFRESH_TOKEN_HEADER) String refreshToken){
        AuthResponse response = authUseCase.reissue(refreshToken);
        return ApplicationResponse.ok(response);
    }

    @DeleteMapping("/logout")
    public ApplicationResponse<Void> logout(@RequestHeader(AuthConsts.REFRESH_TOKEN_HEADER) String refreshToken){
        logoutUseCase.logoutAccessUser(refreshToken);

        // 로그아웃 시 토큰 제거
        noticeFcmUseCase.deleteFcmToken();

        return ApplicationResponse.ok(null);
    }
}
