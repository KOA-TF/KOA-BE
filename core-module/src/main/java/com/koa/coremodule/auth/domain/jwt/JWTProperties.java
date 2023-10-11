package com.koa.coremodule.auth.domain.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
//jwt 프리픽스로 시작하는 속성 값들이 JWTProperties 클래스의 필드에 주입
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    private final String secret;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;
    private final long reissueRefreshTokenExpirationTime;
}
