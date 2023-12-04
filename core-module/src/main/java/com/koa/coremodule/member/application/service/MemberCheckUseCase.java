package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.response.CheckEmailResponse;
import com.koa.coremodule.member.application.dto.response.CheckPasswordResponse;
import com.koa.coremodule.member.application.dto.response.CheckRegisterResponse;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCheckUseCase {

    private final MemberUtils memberUtils;
    private final MemberQueryService memberQueryService;

    public CheckRegisterResponse checkMemberRegistered(String email, String password) {
        boolean isMember = memberQueryService.checkMemberExist(email, password);
        Member member = isMember ? memberQueryService.findByEmail(email) : null;
        return new CheckRegisterResponse(isMember, member != null && member.isRegistered());
    }

    public CheckPasswordResponse checkPassword(String password) {
        Member member = memberUtils.getAccessMember();
        return new CheckPasswordResponse(member.checkPassword(password));
    }

    public CheckEmailResponse checkEmail(String email) {
        final boolean isEmailExist = memberQueryService.checkEmailExist(email);
        return new CheckEmailResponse(isEmailExist);
    }
}
