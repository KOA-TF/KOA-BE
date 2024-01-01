package com.koa.coremodule.member.application.dto.response;

import com.koa.coremodule.member.domain.entity.Part;
import java.util.List;

public record MemberSearchResponse(String name, Part part, String period,
                                   String profileImage, String description,
                                   String major, String email, String phoneNumber,
                                   List<InterestInfoResponse> interests, List<LinkInfoResponse> links) {
}
