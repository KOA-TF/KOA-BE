package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.request.MemberPasswordChangeRequest;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class MemberPasswordChangeUseCase {

    private final MemberUtils memberUtils;

    public void changePassword(MemberPasswordChangeRequest request) {
        Member member = memberUtils.getAccessMember();
        member.updatePassword(request.getPassword());
    }
}
