package com.koa.coremodule.admin.application.mapper;

import com.koa.coremodule.admin.application.dto.AdminMemberList;
import com.koa.coremodule.admin.application.dto.AdminReportList;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.report.domain.entity.Report;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminMapper {

    public static AdminMemberList mapToMemberList(Member member) {

        AdminMemberList memberList = AdminMemberList.builder()
                .id(member.getId())
                .password(member.getPassword())
                .name(member.getName())
                .email(member.getEmail())
                .period(member.getPeriod())
                .phoneNumber(member.getPhoneNumber())
                .birthday(member.getPassword())
                .authority(member.getAuthority())
                .build();
        return memberList;
    }

    public static AdminReportList mapToReportList(Report report) {

        AdminReportList reportList = AdminReportList.builder()
                .no(report.getId())
                .writer(report.getMember().getName())
                .comment(report.getComment().getContent())
                .category(report.getContent())
                .isHided(report.isHided())
                .build();
        return reportList;
    }

}
