package com.koa.coremodule.member.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CheckPasswordResponse {
    private final Boolean isCorrect;
}
