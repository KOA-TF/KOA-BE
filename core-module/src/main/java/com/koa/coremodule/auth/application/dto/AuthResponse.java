package com.koa.coremodule.auth.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class AuthResponse {
    private final String accessToken;
    private final String refreshToken;
}
