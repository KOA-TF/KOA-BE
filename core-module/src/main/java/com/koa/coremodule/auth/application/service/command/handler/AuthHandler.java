package com.koa.coremodule.auth.application.service.command.handler;

import com.koa.coremodule.auth.application.dto.AuthRequest;

public interface AuthHandler {
    String handle(AuthRequest authenticationInfo);

    boolean isAccessible(AuthRequest authenticationInfo);
}
