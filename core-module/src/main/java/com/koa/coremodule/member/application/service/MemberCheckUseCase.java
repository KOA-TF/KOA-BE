package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.application.exception.WrongPasswordException;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCheckUseCase {

    private final MemberUtils memberUtils;
    private final MemberQueryService memberQueryService;

    public void checkMemberRegistered(String email, String password) {
       memberQueryService.checkMemberRegistered(email, password);
    }

    public void checkPassword(String password) {
        Member member = memberUtils.getAccessMember();
        if(!member.getPassword().equals(password)) {
            throw new WrongPasswordException(Error.WRONG_PASSWORD);
        }
    }
}
