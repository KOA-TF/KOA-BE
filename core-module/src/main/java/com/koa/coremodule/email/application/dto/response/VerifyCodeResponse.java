package com.koa.coremodule.email.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VerifyCodeResponse {
    private final Boolean isVerified;
}
