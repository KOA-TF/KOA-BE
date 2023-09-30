package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.exception.InvalidAuthorizationTypeException;
import org.springframework.util.StringUtils;

import java.util.Objects;

@ApplicationService
public class JWTExtractTokenUseCase {
    public String extractToken(final String tokenHeader) {
        if(!StringUtils.hasText(tokenHeader)){
            throw new InvalidAuthorizationTypeException(Error.EMPTY_AUTHORIZATION_HEADER);
        }

        final String[] splitToken = tokenHeader.split(" ");
        final String authorizationType = splitToken[0];
        final String accessToken = splitToken[1];
        if(!Objects.equals(authorizationType, AuthConsts.AUTHENTICATION_TYPE)){
            throw new InvalidAuthorizationTypeException(Error.INVALID_AUTHORIZATION_TYPE);
        }
        return accessToken;
    }
}
