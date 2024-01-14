package com.koa.coremodule.member.application.dto.response;

import com.koa.coremodule.member.domain.entity.Part;

public record MemberInfoListResponse(Long memberId, String name, Part part, String profileImage, String description) {
}
