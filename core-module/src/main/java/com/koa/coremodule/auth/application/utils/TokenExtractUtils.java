package com.koa.coremodule.auth.application.utils;

import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.exception.InvalidAuthorizationTypeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import com.koa.commonmodule.exception.Error;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenExtractUtils {

    public static String extractToken(final String tokenHeader) {
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
