package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.domain.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class JWTExtractEmailUseCase {
    private final JWTProvider jwtProvider;

    public String extractEmail(final String token){
        return jwtProvider.extractEmailFromAccessToken(token);
    }
}
