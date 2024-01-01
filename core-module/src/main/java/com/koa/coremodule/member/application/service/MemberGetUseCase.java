package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.response.MemberHomeResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.application.mapper.MemberMapper;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.service.MemberDetailQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberGetUseCase {

    private final MemberUtils memberUtils;
    private final MemberDetailQueryService memberDetailQueryService;

    public MemberInfoResponse getMemberInfo(){
        final Member member = memberUtils.getAccessMember();
        boolean isMemberDetailExist = memberDetailQueryService.isMemberDetailExist(member.getId());
        return MemberMapper.mapToMemberInfoResponse(member, isMemberDetailExist);
    }

    public MemberHomeResponse getMemberHomeInfo(){
        final Member member = memberUtils.getAccessMember();
        final MemberDetail memberDetail = memberDetailQueryService.findMemberDetailByMemberId(member.getId());
        return MemberMapper.mapToMemberHomeResponse(member, memberDetail);
    }
}
