package com.koa.coremodule.auth.application.utils;

import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.exception.InvalidAuthorizationTypeException;
import com.koa.coremodule.auth.domain.exception.AuthError;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenExtractUtils {

    public static String extractToken(final String tokenHeader) {
        if(!StringUtils.hasText(tokenHeader)){
            throw new InvalidAuthorizationTypeException(AuthError.EMPTY_AUTHORIZATION_HEADER);
        }

        final String[] splitToken = tokenHeader.split(" ");
        final String authorizationType = splitToken[0];
        final String accessToken = splitToken[1];
        if(!Objects.equals(authorizationType, AuthConsts.AUTHENTICATION_TYPE)){
            throw new InvalidAuthorizationTypeException(AuthError.INVALID_AUTHORIZATION_TYPE);
        }
        return accessToken;
    }
}
