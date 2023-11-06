package com.koa.coremodule.member.application.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberDetailInfoResponse {
    private String major;
    private String part;
    private List<InterestInfoResponse> interests;
    private String phoneNumber;
    private String description;
    private String profileImage;
    private List<LinkInfoResponse> links;
}
