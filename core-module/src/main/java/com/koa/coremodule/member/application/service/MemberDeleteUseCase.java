package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.handler.event.MemberAccountDeleteEvent;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberDeleteService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class MemberDeleteUseCase {

    private final MemberUtils userUtils;
    private final MemberDeleteService memberDeleteService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void deleteMember(){
        final Member member = userUtils.getAccessMember();
        memberDeleteService.deleteMember(member.getId());
        applicationEventPublisher.publishEvent(new MemberAccountDeleteEvent(member.getId()));
    }
}
