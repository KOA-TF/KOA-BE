package com.koa.coremodule.notice.repository;

import com.koa.coremodule.notice.application.dto.CurriculumListRequest;
import com.koa.coremodule.notice.application.dto.NoticeListRequest;
import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;

import java.util.List;

public interface NoticeDynamicRepository {

    List<NoticeListProjection> findAllNotice(NoticeListRequest request);

    Integer findViewYn(NoticeViewRequest request);

    List<CurriculumProjection> findByCurriculum();

    List<Notice> selectNoticeByCurriculum(CurriculumListRequest request);

}
