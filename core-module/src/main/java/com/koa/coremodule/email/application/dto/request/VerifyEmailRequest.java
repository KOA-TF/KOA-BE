package com.koa.coremodule.email.application.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyEmailRequest {
    private String email;

    public VerifyEmailRequest(String email) {
        this.email = email;
    }
}
