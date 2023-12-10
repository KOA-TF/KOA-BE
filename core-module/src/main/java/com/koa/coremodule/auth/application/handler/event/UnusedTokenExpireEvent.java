package com.koa.coremodule.auth.application.handler.event;

import com.koa.coremodule.auth.domain.entity.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnusedTokenExpireEvent {
    private final String email;
    private final TokenType tokenType;
}
