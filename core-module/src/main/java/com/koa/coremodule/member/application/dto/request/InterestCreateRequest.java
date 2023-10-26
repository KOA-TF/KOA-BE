package com.koa.coremodule.member.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterestCreateRequest {
    private String category;
    private String content;
}
