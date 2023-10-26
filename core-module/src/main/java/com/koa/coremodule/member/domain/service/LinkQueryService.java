package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.entity.Link;
import com.koa.coremodule.member.domain.repository.LinkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LinkQueryService {
    private final LinkRepository linkRepository;

    public List<Link> findLinksByMemberDetailId(Long memberDetailId) {
        return linkRepository.findAllByMemberDetailId(memberDetailId);
    }
}
