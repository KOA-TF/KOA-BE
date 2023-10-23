package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.notice.application.dto.NoticeListResponse;
import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.repository.NoticeRepository;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeDetailProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeQueryService {

    private final NoticeRepository noticeRepository;

    public List<NoticeListProjection> selectNotice(Long memberId) {

        List<NoticeListProjection> projection = noticeRepository.findAllNotice(memberId);

        return projection;
    }

    public void findViewYn(List<NoticeListResponse> response, Long memberId, List<NoticeListProjection> projection) {

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
    }

    public List<CurriculumProjection> selectCurriculum() {

        List<CurriculumProjection> projection = noticeRepository.findByCurriculum();

        return projection;
    }

    public List<Notice> selectCurriculumList(Long curriculumId) {

        List<Notice> entityResponse = noticeRepository.selectNoticeByCurriculum(curriculumId);

        return entityResponse;
    }

    public Notice save(Notice notice) {

        return noticeRepository.save(notice);
    }

    public NoticeDetailProjection selectNoticeDetail(Long noticeId) {

        NoticeDetailProjection projection = noticeRepository.findNoticeDetailById(noticeId);

        return projection;
    }

}
