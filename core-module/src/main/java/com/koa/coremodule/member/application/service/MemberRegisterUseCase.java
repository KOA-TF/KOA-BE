package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.application.dto.request.MemberRegisterRequest;
import com.koa.coremodule.member.application.exception.WrongPasswordException;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class MemberRegisterUseCase {

    private final MemberQueryService memberQueryService;

    public void registerMember(MemberRegisterRequest memberRegisterRequest) {
        Member member = memberQueryService.findByEmail(memberRegisterRequest.getEmail());
        final boolean isPasswordMatch = member.checkPassword(memberRegisterRequest.getPassword());
        if(!isPasswordMatch) {
            throw new WrongPasswordException(Error.MEMBER_NOT_FOUND);
        }
        member.register();
    }
}
