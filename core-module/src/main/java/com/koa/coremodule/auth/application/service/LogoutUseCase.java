package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.application.utils.TokenExtractUtils;
import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.service.TokenDeleteService;
import com.koa.coremodule.auth.domain.service.TokenQueryService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class LogoutUseCase {
    private final TokenDeleteService tokenDeleteService;
    private final TokenQueryService tokenQueryService;

    public void logoutAccessUser(String refreshTokenHeader) {
        final String refreshToken = TokenExtractUtils.extractToken(refreshTokenHeader);
        final Token refreshTokenEntity = tokenQueryService.findTokenByValue(refreshToken, TokenType.REFRESH_TOKEN);
        tokenDeleteService.deleteToken(refreshTokenEntity);
    }

}
