package com.koa.coremodule.auth.domain.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JWTConsts {
    public static final String TOKEN_ISSUER = "koa";
    public static final String EMAIL ="email";
    public static final String TOKEN_TYPE = "token_type";
}
