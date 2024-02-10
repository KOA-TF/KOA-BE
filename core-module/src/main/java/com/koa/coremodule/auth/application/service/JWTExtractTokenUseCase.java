package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.application.utils.TokenExtractUtils;

@ApplicationService
public class JWTExtractTokenUseCase {
    public String extractToken(final String tokenHeader) {
        return TokenExtractUtils.extractToken(tokenHeader);
    }
}
