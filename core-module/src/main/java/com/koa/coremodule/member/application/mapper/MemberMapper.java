package com.koa.coremodule.member.application.mapper;

import com.koa.coremodule.member.application.dto.MemberInfo;
import com.koa.coremodule.member.domain.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static MemberInfo mapToMemberInfo(Member member){
        return MemberInfo.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}
