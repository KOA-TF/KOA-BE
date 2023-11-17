package com.koa.coremodule.notice.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class NoticeUpdateRequest {
    private Long memberId;
    private Long noticeId;
    private Long teamId;
    private Long curriculumId;
    private String title;
    private String content;
}
