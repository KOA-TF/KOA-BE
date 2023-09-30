package com.koa.coremodule.auth.domain.entity;

import lombok.Getter;

@Getter
public enum TokenType {
    ACCESS_TOKEN,
    REFRESH_TOKEN
}
