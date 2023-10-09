package com.koa.apimodule.command.api.notice.service;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.application.service.NoticeFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeFindUseCase {

    private final NoticeFacadeService noticeFacadeService;

    public List<NoticeListResponse> selectNotice(NoticeListRequest request) {

        return noticeFacadeService.selectNotice(request);
    }

    public List<CurriculumResponse> selectCurriculum() {

        return noticeFacadeService.selectCurriculum();
    }

    public List<CurriculumListResponse> selectCurriculumList(CurriculumListRequest request) {

        return noticeFacadeService.selectCurriculumList(request);
    }

}
