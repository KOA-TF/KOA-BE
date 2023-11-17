package com.koa.coremodule.notice.application.dto;

import com.koa.coremodule.notice.domain.entity.ViewType;

public record NoticeViewResponse(Long id, ViewType viewType) {
}
