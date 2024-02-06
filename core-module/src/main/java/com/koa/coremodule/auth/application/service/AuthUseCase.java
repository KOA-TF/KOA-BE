package com.koa.coremodule.auth.application.service;

import com.google.rpc.context.AttributeContext.Auth;
import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.dto.AuthRequest;
import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.dto.LoginRequest;
import com.koa.coremodule.auth.application.service.command.AuthInvoker;
import com.koa.coremodule.auth.application.utils.TokenExtractUtils;
import com.koa.coremodule.auth.domain.jwt.JWTProvider;
import com.koa.coremodule.auth.domain.service.TokenDeleteService;
import com.koa.coremodule.member.domain.entity.Authority;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;


@ApplicationService
@RequiredArgsConstructor
public class AuthUseCase {
    private final JWTProvider jwtProvider;
    private final TokenDeleteService tokenDeleteService;
    private final AuthInvoker authInvoker;


    public AuthResponse authLogin(Authority authority, LoginRequest loginRequest){
        return authInvoker.execute(new AuthRequest(authority, loginRequest.getEmail(), loginRequest.getPassword()));
    }

    public AuthResponse reissue(String refreshToken){
        final String token = TokenExtractUtils.extractToken(refreshToken);
        String reIssueAccessToken = attachAuthenticationType(jwtProvider::reIssueAccessToken, token);
        String reIssueRefreshToken = attachAuthenticationType(jwtProvider::reIssueRefreshToken, token);
        tokenDeleteService.deleteTokenByTokenValue(refreshToken);
        return AuthResponse.builder()
                .accessToken(reIssueAccessToken)
                .refreshToken(reIssueRefreshToken)
                .build();
    }

    private <T> String attachAuthenticationType(Function<T, String> generateTokenMethod, T includeClaimData) {
        return AuthConsts.AUTHENTICATION_TYPE_PREFIX + generateTokenMethod.apply(includeClaimData);
    }
}
