package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.application.dto.AuthRequest;
import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.service.command.AuthInvoker;
import com.koa.coremodule.member.domain.entity.Authority;
import lombok.RequiredArgsConstructor;


@ApplicationService
@RequiredArgsConstructor
public class AuthUseCase {
    private final AuthInvoker oAuthInvoker;

    public AuthResponse authLogin(Authority authority, String email, String password){
        return oAuthInvoker.execute(new AuthRequest(authority, email, password));
    }
}
