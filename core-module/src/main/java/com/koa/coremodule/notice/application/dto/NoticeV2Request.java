package com.koa.coremodule.notice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeV2Request {
    private Long memberId;
    private Long teamId;
    private Long curriculumId;
    private String noticeTitle;
    private String content;
    private String voteTitle;
    private ArrayList<String> item;
}
