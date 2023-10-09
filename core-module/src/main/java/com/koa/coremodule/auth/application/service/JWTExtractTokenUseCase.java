package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.exception.InvalidAuthorizationTypeException;
import com.koa.coremodule.auth.application.utils.TokenExtractUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

@ApplicationService
public class JWTExtractTokenUseCase {
    public String extractToken(final String tokenHeader) {
        return TokenExtractUtils.extractToken(tokenHeader);
    }
}
