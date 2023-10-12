package com.koa.apimodule.command.api.notice.service;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.application.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeFindUseCase {

    private final NoticeQueryService noticeQueryService;

    public List<NoticeListResponse> selectNotice(NoticeListRequest request) {

        return noticeQueryService.selectNotice(request);
    }

    public List<CurriculumResponse> selectCurriculum() {

        return noticeQueryService.selectCurriculum();
    }

    public List<CurriculumListResponse> selectCurriculumList(CurriculumListRequest request) {

        return noticeQueryService.selectCurriculumList(request);
    }

}
