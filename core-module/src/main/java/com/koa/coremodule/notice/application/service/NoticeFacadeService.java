package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.notice.application.dto.NoticeListRequest;
import com.koa.coremodule.notice.application.dto.NoticeListResponse;
import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.mapper.NoticeMapper;
import com.koa.coremodule.notice.repository.NoticeRepository;
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

}
