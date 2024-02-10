package com.koa.coremodule.admin.application.dto;

import com.koa.coremodule.member.domain.entity.Authority;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AdminMemberReq {

    private String period;
    private String name;
    private String phoneNumber;
    private String birthday;
    private String password;
    private String email;
    private Authority authority;

}
