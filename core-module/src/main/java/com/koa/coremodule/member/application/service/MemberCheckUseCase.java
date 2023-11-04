package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.response.RegisterResponse;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCheckUseCase {

    private final MemberQueryService memberQueryService;

    public void checkMemberRegistered(String email, String password) {
       memberQueryService.checkMemberRegistered(email, password);
    }
}
