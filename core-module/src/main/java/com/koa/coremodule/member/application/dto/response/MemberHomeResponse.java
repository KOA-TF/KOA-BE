package com.koa.coremodule.member.application.dto.response;

import com.koa.coremodule.member.domain.entity.Part;

public record MemberHomeResponse(String name, String period, Part part) {
}