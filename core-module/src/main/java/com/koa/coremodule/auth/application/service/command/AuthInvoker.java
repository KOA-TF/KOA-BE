package com.koa.coremodule.auth.application.service.command;

import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.dto.AuthRequest;
import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.exception.AuthException;
import com.koa.coremodule.auth.application.handler.event.UnusedTokenExpireEvent;
import com.koa.coremodule.auth.application.service.command.handler.AuthHandler;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.jwt.JWTProvider;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.koa.commonmodule.exception.Error;

@Component
@RequiredArgsConstructor
public class AuthInvoker {
    private final List<AuthHandler> authHandlerList;
    private final JWTProvider jwtProvider;

    private final ApplicationEventPublisher publisher;
    public AuthResponse execute(AuthRequest request){
        String email = attemptLogin(request);
        publishEvent(email);
        return generateServerAuthenticationTokens(email);
    }

    private void publishEvent(String email) {
        publisher.publishEvent(new UnusedTokenExpireEvent(email, TokenType.REFRESH_TOKEN));
    }

    private String attemptLogin(AuthRequest request) {
        for (AuthHandler authHandler : authHandlerList) {
            if (authHandler.isAccessible(request)) {
                return authHandler.handle(request);
            }
        }
        throw new AuthException(Error.AUTH_FAIL);
    }

    private AuthResponse generateServerAuthenticationTokens(String email) {
        final String accessToken = attachAuthenticationType(jwtProvider::generateAccessToken, email);
        final String refreshToken = attachAuthenticationType(jwtProvider::generateRefreshToken, email);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private <T> String attachAuthenticationType(Function<T, String> generateTokenMethod, T includeClaimData) {
        return AuthConsts.AUTHENTICATION_TYPE_PREFIX + generateTokenMethod.apply(includeClaimData);
    }
}