package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeDetailListProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeListProjection;

import java.util.List;

public interface NoticeDynamicRepository {

    List<NoticeListProjection> findAllNotice();

    NoticeDetailListProjection findAllNoticeDetail(Long noticeId);

    ViewType findViewYn(NoticeViewRequest request);

    List<CurriculumProjection> findByCurriculum();

    List<Notice> selectNoticeByCurriculum(Long curriculumId);

}
