package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.exception.AuthException;
import com.koa.coremodule.auth.application.utils.TokenExtractUtils;
import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.jwt.JWTProvider;
import com.koa.coremodule.auth.domain.service.TokenDeleteService;
import com.koa.coremodule.auth.domain.service.TokenQueryService;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;


@ApplicationService
@RequiredArgsConstructor
public class AuthUseCase {
    private final JWTProvider jwtProvider;
    private final MemberQueryService memberQueryService;

    public AuthResponse authLogin(Authority authority, String email, String password){
        memberQueryService.checkAccountExist(authority, email, password);
        final String accessToken = attachAuthenticationType(jwtProvider::generateAccessToken, email);
        final String refreshToken = attachAuthenticationType(jwtProvider::generateRefreshToken, email);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

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
