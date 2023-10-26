package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.response.RegisterResponse;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRegisterGetUseCase {

    private final MemberQueryService memberQueryService;

    public RegisterResponse getMemberRegistered(String email, String password) {
        boolean isRegistered = memberQueryService.checkRegister(email, password);
        return new RegisterResponse(isRegistered);
    }
}
