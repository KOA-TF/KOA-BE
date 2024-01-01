package com.koa.coremodule.member.application.handler;

import com.koa.coremodule.member.application.handler.event.LinkUpdateEvent;
import com.koa.coremodule.member.domain.service.LinkDeleteService;
import com.koa.coremodule.member.domain.service.LinkSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class LinkUpdateHandler {

    private final LinkDeleteService linkDeleteService;
    private final LinkSaveService linkSaveService;

    @EventListener
    public void updateLink(LinkUpdateEvent linkUpdateEvent) {
        if(!linkUpdateEvent.getLinks().isEmpty()) {
            linkDeleteService.deleteLinkByMemberDetailId(linkUpdateEvent.getMemberDetailId());
            linkSaveService.saveAll(linkUpdateEvent.getLinks());
        }
    }
}
