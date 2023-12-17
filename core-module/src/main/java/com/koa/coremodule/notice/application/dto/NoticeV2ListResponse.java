package com.koa.coremodule.notice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeV2ListResponse {

    private Long noticeId;
    private String curriculumName;
    private String teamName;
    private String title;
    private String content;
    private List<String> imageUrl;
    private LocalDate date;
    private Boolean viewYn;
    private Boolean voteYn;
    private Boolean voteAttendYn;

}
