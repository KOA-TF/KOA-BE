package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.entity.Link;
import com.koa.coremodule.member.domain.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class LinkSaveService {
    private final LinkRepository linkRepository;

    public void saveLinkEntity(Link link){
        linkRepository.save(link);
    }
}
