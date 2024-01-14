package com.koa.coremodule.member.application.dto.request;

import com.koa.coremodule.member.domain.entity.Part;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDetailCreateRequest {

    private String major;
    private Part part;
    private List<InterestCreateRequest> interests;
    private String description;
    private List<LinkCreateRequest> links;

}
