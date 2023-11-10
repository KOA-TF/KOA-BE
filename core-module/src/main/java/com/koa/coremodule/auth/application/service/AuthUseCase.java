package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.dto.AuthRequest;
import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.service.command.AuthInvoker;
import com.koa.coremodule.auth.application.utils.TokenExtractUtils;
import com.koa.coremodule.auth.domain.jwt.JWTProvider;
import com.koa.coremodule.member.domain.entity.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@ApplicationService
@RequiredArgsConstructor
public class AuthUseCase {
    private final AuthInvoker oAuthInvoker;
    private final JWTProvider jwtProvider;

    public AuthResponse authLogin(Authority authority, String email, String password){
        return oAuthInvoker.execute(new AuthRequest(authority, email, password));
    }

    public AuthResponse reissue(String refreshToken){
        final String token = TokenExtractUtils.extractToken(refreshToken);
        String reIssueAccessToken = AuthConsts.AUTHENTICATION_TYPE_PREFIX+jwtProvider.reIssueAccessToken(token);
        String reIssueRefreshToken = AuthConsts.AUTHENTICATION_TYPE_PREFIX+jwtProvider.reIssueRefreshToken(token);
        return AuthResponse.builder()
                .accessToken(reIssueAccessToken)
                .refreshToken(reIssueRefreshToken)
                .build();
    }
}
