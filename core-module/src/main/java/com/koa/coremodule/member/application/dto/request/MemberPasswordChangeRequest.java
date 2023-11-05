package com.koa.coremodule.member.application.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPasswordChangeRequest {
    private String password;

    public MemberPasswordChangeRequest(String password) {
        this.password = password;
    }
}
