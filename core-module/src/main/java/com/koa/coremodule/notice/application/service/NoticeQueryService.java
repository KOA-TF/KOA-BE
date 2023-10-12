package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.mapper.NoticeMapper;
import com.koa.coremodule.notice.repository.NoticeRepository;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeQueryService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;

    public List<NoticeListResponse> selectNotice(Long memberId) {

        List<NoticeListProjection> projection = noticeRepository.findAllNotice(memberId);
        List<NoticeListResponse> response = noticeMapper.toNoticeListDTO(projection);

        int i = 0;
        while (i < response.size()) {

            NoticeViewRequest viewRequest = new NoticeViewRequest(memberId, projection.get(i).getNoticeId());

            if (noticeRepository.findViewYn(viewRequest).equals(ViewType.VIEWED)) {
                NoticeListResponse originalResponse = response.get(i);
                NoticeListResponse updatedResponse = originalResponse.withViewYn(true);
                response.set(i, updatedResponse);
            }
            i++;
        }

        return response;
    }

    public List<CurriculumResponse> selectCurriculum() {

        List<CurriculumProjection> projection = noticeRepository.findByCurriculum();
        List<CurriculumResponse> response = noticeMapper.toCurriculumDTO(projection);

        return response;
    }

    public List<CurriculumListResponse> selectCurriculumList(Long curriculumId) {

        List<Notice> entityResponse = noticeRepository.selectNoticeByCurriculum(curriculumId);
        List<CurriculumListResponse> response = noticeMapper.toCurriculumListDTO(entityResponse);

        return response;
    }

}
