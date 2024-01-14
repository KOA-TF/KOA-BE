package com.koa.coremodule.team.application.dto.request;

import java.util.List;

public record EnrollCreateRequest(List<Long> memberIds) {
}
