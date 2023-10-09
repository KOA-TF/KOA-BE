package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.mapper.NoticeMapper;
import com.koa.coremodule.notice.repository.NoticeRepository;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeFacadeService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;

    public List<NoticeListResponse> selectNotice(NoticeListRequest request) {

        List<NoticeListProjection> projection = noticeRepository.findAllNotice(request);
        List<NoticeListResponse> response = noticeMapper.toNoticeListDTO(projection);

        int i = 0;
        while (i < response.size()) {

            NoticeViewRequest viewRequest = new NoticeViewRequest(request.id(), projection.get(i).getNoticeId());

            if (noticeRepository.findViewYn(viewRequest) >= 1) {
                NoticeListResponse originalResponse = response.get(i);
                NoticeListResponse updatedResponse = originalResponse.withViewYn(true);
                response.set(i, updatedResponse);
            }
            i++;
        }

        return response;
    }

    public List<CurriculumResponse> selectCurriculum(CurriculumRequest request) {

        List<CurriculumProjection> projection = noticeRepository.findByCurriculum(request);
        List<CurriculumResponse> response = noticeMapper.toCurriculumDTO(projection);

        return response;
    }

    public List<CurriculumListResponse> selectCurriculumList(CurriculumListRequest request) {

        List<Notice> entityResponse = noticeRepository.selectNoticeByCurriculum(request);
        List<CurriculumListResponse> response = noticeMapper.toCurriculumListDTO(entityResponse);

        return response;
    }

}
