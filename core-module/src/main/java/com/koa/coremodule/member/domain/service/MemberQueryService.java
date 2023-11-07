package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.exception.UserNotFoundException;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public void checkAccountExist(String email, Authority authority) {
        if(!memberRepository.existsByEmailAndAuthority(email,authority)) {
            throw new UserNotFoundException(Error.MEMBER_NOT_FOUND);
        }
    }

    public boolean checkMemberRegistered(String email, String password) {
        return memberRepository.existsByEmailAndPassword(email, password);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(Error.MEMBER_NOT_FOUND));
    }

    public void checkEmailExist(String email) {
        if(memberRepository.existsByEmail(email) == false) {
            throw new UserNotFoundException(Error.MEMBER_NOT_FOUND);
        }
    }
}
