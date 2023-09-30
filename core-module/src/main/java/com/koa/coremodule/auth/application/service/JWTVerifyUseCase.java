package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.domain.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class JWTVerifyUseCase {
    private final JWTProvider jwtProvider;

    public void validateToken(final String token){
        jwtProvider.validateToken(token);
    }
}
