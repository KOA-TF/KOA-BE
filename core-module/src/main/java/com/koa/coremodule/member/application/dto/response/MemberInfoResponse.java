package com.koa.coremodule.member.application.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberInfoResponse {
    private String name;
    private String email;
    private boolean isMemberDetailExist;
}
