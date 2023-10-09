package com.koa.coremodule.notice.application.dto;

import java.time.LocalDate;

public record CurriculumListResponse(String title,
                                     String content,
                                     LocalDate date) {
}
