package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.entity.Interest;
import com.koa.coremodule.member.domain.repository.InterestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestQueryService {
    private final InterestRepository interstRepository;

    public List<Interest> findInterestsByMemberDetailId(Long memberDetailId) {
        return interstRepository.findAllByMemberDetailId(memberDetailId);
    }
}
