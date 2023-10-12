package com.koa.apimodule.command.api.notice.service;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.application.service.NoticeQueryService;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.mapper.NoticeMapper;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeFindUseCase {

    private final NoticeQueryService noticeQueryService;
    private final NoticeMapper noticeMapper;

    public List<NoticeListResponse> selectNotice(Long memberId) {

        List<NoticeListProjection> projection = noticeQueryService.selectNotice(memberId);
        List<NoticeListResponse> response = noticeMapper.toNoticeListDTO(projection);

        noticeQueryService.findViewYn(response, memberId, projection);

        return response;
    }

    public List<CurriculumResponse> selectCurriculum() {

        List<CurriculumProjection> projection = noticeQueryService.selectCurriculum();
        List<CurriculumResponse> response = noticeMapper.toCurriculumDTO(projection);

        return response;
    }

    public List<CurriculumListResponse> selectCurriculumList(Long curriculumId) {

        List<Notice> entityResponse = noticeQueryService.selectCurriculumList(curriculumId);
        List<CurriculumListResponse> response = noticeMapper.toCurriculumListDTO(entityResponse);

        return response;
    }

}
