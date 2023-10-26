package com.koa.coremodule.notice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeRequest {

    private Long memberId;
    private Long teamId;
    private Long curriculumId;
    private String imageUrl;
    private String title;
    private String content;

}
