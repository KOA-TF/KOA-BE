package com.koa.coremodule.member.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LinkCreateRequest {
    private String type;
    private String link;
}
