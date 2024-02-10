package com.koa.coremodule.admin.application.service;

import com.koa.coremodule.admin.application.dto.AdminMemberList;
import com.koa.coremodule.admin.application.dto.AdminMemberReq;
import com.koa.coremodule.admin.application.mapper.AdminMapper;
import com.koa.coremodule.admin.domain.service.AdminQueryService;
import com.koa.coremodule.member.application.handler.event.MemberAccountDeleteEvent;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberDeleteService;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminSaveUseCase {

    private final AdminQueryService adminQueryService;
    private final MemberDeleteService memberDeleteService;
    private final MemberQueryService memberQueryService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Long addMember(AdminMemberReq memberList) {

        Member member = AdminMapper.mapToMember(memberList);
        return adminQueryService.saveMember(member);
    }

    public Long updateMember(AdminMemberList memberList) {

        Member member = memberQueryService.findMemberById(memberList.getId());
        member.updateMember(memberList);

        return member.getId();
    }

    public void deleteMember(Long memberId) {

        memberDeleteService.deleteMember(memberId);
        applicationEventPublisher.publishEvent(new MemberAccountDeleteEvent(memberId));
    }

}
