package com.koa.coremodule.member.application.mapper;

import com.koa.coremodule.member.application.dto.response.MemberHomeResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static MemberInfoResponse mapToMemberInfoResponse(Member member, boolean isMemberDetailExist){
        return MemberInfoResponse.builder()
                .name(member.getName())
                .period(member.getPeriod())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .isMemberDetailExist(isMemberDetailExist)
                .build();
    }

    public static MemberHomeResponse mapToMemberHomeResponse(Member member, MemberDetail memberDetail){
        return new MemberHomeResponse(member.getName(), member.getPeriod(), memberDetail.getPart());
    }
}
