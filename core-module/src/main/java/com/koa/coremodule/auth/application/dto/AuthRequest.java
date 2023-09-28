package com.koa.coremodule.auth.application.dto;

import com.koa.coremodule.member.domain.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequest {
    private Authority authority;
    private String email;
    private String password;
}
