package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.repository.MemberDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberDetailSaveService {
    private final MemberDetailRepository memberInfoRepository;

    public void saveMemberInfoEntity(MemberDetail memberInfo){
        memberInfoRepository.save(memberInfo);
    }
}
