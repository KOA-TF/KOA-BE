package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.MemberInfo;
import com.koa.coremodule.member.application.mapper.MemberMapper;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberInfoGetUseCase {

    private final MemberUtils memberUtils;

    public MemberInfo getMemberInfo(){
        final Member member = memberUtils.getAccessMember();
        return MemberMapper.mapToMemberInfo(member);
    }
}
