package com.koa.coremodule.notice.application.mapper;

import com.koa.coremodule.notice.application.dto.NoticeDetailInfoResponse;
import com.koa.coremodule.notice.application.dto.NoticeDetailListResponse;
import com.koa.coremodule.notice.application.dto.NoticeV2DetailListResponse;

import java.util.ArrayList;

public final class NoticeDetailMapper {

    private NoticeDetailMapper() {
    }

    public static NoticeV2DetailListResponse toDetailMapper(NoticeDetailInfoResponse request) {
        return NoticeV2DetailListResponse.builder()
                .noticeId(request.noticeId())
                .name(request.name())
                .profileImage(request.profileImage())
                .curriculum(request.curriculum())
                .team(request.team())
                .title(request.title())
                .content(request.content())
                .imageUrl(new ArrayList<>())
                .date(request.date())
                .viewYn(request.viewYn())
                .voteId(0L)
                .build();
    }

}
