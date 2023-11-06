package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.repository.MemberDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberDetailDeleteService {

    private final MemberDetailRepository memberDetailRepository;

    public void deleteMemberDetailByMemberId(Long memberId) {
        memberDetailRepository.deleteByMemberId(memberId);
    }
}
