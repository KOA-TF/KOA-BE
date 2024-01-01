package com.koa.coremodule.member.application.dto.request;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDetailUpdateRequest {
    private String major;
    private String part;
    private List<InterestCreateRequest> interests;
    private String description;
    private List<LinkCreateRequest> links;
}
