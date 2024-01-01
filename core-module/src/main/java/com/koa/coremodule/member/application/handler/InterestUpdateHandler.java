package com.koa.coremodule.member.application.handler;

import com.koa.coremodule.member.application.handler.event.InterestUpdateEvent;
import com.koa.coremodule.member.domain.service.InterestDeleteService;
import com.koa.coremodule.member.domain.service.InterestSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class InterestUpdateHandler {

    private final InterestDeleteService interestDeleteService;
    private final InterestSaveService interestSaveService;

    @EventListener
    public void updateInterest(InterestUpdateEvent interestUpdateEvent) {
        if(!interestUpdateEvent.getInterests().isEmpty()) {
            interestDeleteService.deleteInterestByMemberDetailId(interestUpdateEvent.getMemberDetailId());
            interestSaveService.saveAll(interestUpdateEvent.getInterests());
        }

    }
}
