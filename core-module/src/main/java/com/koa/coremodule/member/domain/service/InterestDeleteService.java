package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.repository.InterestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class InterestDeleteService {

    private final InterestRepository interestRepository;

    public void deleteInterestByMemberDetailId(final Long memberDetailId){
        interestRepository.deleteByMemberDetailId(memberDetailId);
    }
}
