package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberRepository memberRepository;

    public void deleteMember(final Long memberId){
        memberRepository.deleteById(memberId);
    }
}
