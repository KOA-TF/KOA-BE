package com.koa.coremodule.member.domain.utils;

import com.koa.commonmodule.utils.SecurityUtils;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberUtils {
    private final MemberQueryService memberQueryService;

    public Member getAccessMember(){
        final String email = SecurityUtils.getAuthenticationPrincipal();
        return memberQueryService.findByEmail(email);
    }

    public static String getEmailFromAccessUser(){
        return SecurityUtils.getAuthenticationPrincipal();
    }
}
