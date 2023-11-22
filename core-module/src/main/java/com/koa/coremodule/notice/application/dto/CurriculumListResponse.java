package com.koa.coremodule.notice.application.dto;

import java.time.LocalDateTime;

public record CurriculumListResponse(String title,
                                     String content,
                                     LocalDateTime date) {
}
