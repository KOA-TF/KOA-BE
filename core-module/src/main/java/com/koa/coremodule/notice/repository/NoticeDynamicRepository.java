package com.koa.coremodule.notice.repository;

import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;

import java.util.List;

public interface NoticeDynamicRepository {

    List<NoticeListProjection> findAllNotice(Long memberId);

    ViewType findViewYn(NoticeViewRequest request);

    List<CurriculumProjection> findByCurriculum();

    List<Notice> selectNoticeByCurriculum(Long curriculumId);

}
