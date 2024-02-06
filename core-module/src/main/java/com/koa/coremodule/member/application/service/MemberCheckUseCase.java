package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.request.CheckEmailRequest;
import com.koa.coremodule.member.application.dto.request.CheckPasswordRequest;
import com.koa.coremodule.member.application.dto.request.MemberRegisterRequest;
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

    public CheckRegisterResponse checkMemberRegistered(MemberRegisterRequest memberRegisterRequest) {
        boolean isMember = memberQueryService.checkMemberExist(memberRegisterRequest.getEmail(), memberRegisterRequest.getPassword());
        Member member = isMember ? memberQueryService.findByEmail(memberRegisterRequest.getEmail()) : null;
        return new CheckRegisterResponse(isMember, member != null && member.isRegistered());
    }

    public CheckPasswordResponse checkPassword(CheckPasswordRequest checkPasswordRequest) {
        Member member = memberUtils.getAccessMember();
        return new CheckPasswordResponse(member.checkPassword(checkPasswordRequest.getPassword()));
    }

    public CheckEmailResponse checkEmail(CheckEmailRequest checkEmailRequest) {
        final boolean isEmailExist = memberQueryService.checkEmailExist(checkEmailRequest.getEmail());
        return new CheckEmailResponse(isEmailExist);
    }
}
