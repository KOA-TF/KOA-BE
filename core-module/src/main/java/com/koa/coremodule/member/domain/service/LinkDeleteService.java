package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.repository.LinkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class LinkDeleteService {

    private final LinkRepository linkRepository;

    public void deleteLinkByMemberDetailId(final Long memberDetailId){
        linkRepository.deleteByMemberDetailId(memberDetailId);
    }
}
