package com.koa.coremodule.auth.application.service.command.handler.impl;

import com.koa.coremodule.auth.application.dto.AuthRequest;
import com.koa.coremodule.auth.application.service.command.handler.AuthHandler;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExecutiveAuthHandler implements AuthHandler {
    private static final Authority AUTH_TYPE = Authority.EXECUTIVE;
    private final MemberQueryService memberQueryService;

    @Override
    public String handle(AuthRequest authenticationInfo) {
        memberQueryService.checkExecutiveAccountExist(authenticationInfo.getAuthority(),authenticationInfo.getEmail(),authenticationInfo.getPassword());
        return authenticationInfo.getEmail();
    }

    @Override
    public boolean isAccessible(AuthRequest authenticationInfo) {
        return AUTH_TYPE.equals(authenticationInfo.getAuthority());
    }


}