package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.application.mapper.CurriculumMapper;
import com.koa.coremodule.notice.application.mapper.NoticeMapper;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeListProjection;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import com.koa.coremodule.vote.domain.service.VoteFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeGetUseCase {

    private final NoticeQueryService noticeQueryService;
    private final VoteFindService voteFindService;
    private final NoticeMapper noticeMapper;

    public List<NoticeListResponse> selectNotice(Long memberId) {

        List<NoticeListProjection> projection = noticeQueryService.selectNotice();
        List<NoticeListResponse> response = noticeMapper.toNoticeListDTO(projection);

        noticeQueryService.findViewYn(response, memberId, projection);

        return response;
    }

    public List<NoticeV2ListResponse> selectNoticeV2(NoticeSelectRequest request) {
        List<NoticeListProjection> projection = noticeQueryService.selectNoticeV2(request);
        List<NoticeListResponse> response = noticeMapper.toNoticeListDTO(projection);
        List<NoticeV2ListResponse> results = new ArrayList<>();
        Map<Long, NoticeV2ListResponse> noticeMap = new HashMap<>();

        noticeQueryService.findViewYn(response, request.memberId(), projection);

        for (NoticeListResponse p : response) {
            if (!noticeMap.containsKey(p.noticeId())) {
                final NoticeV2ListResponse v2ListResponse = NoticeV2ListResponse.builder()
                        .noticeId(p.noticeId())
                        .curriculumName(p.curriculumName())
                        .teamName(p.teamName())
                        .title(p.title())
                        .content(p.content())
                        .date(p.date())
                        .viewYn(p.viewYn())
                        .voteYn(false)
                        .voteAttendYn(false)
                        .imageUrl(new ArrayList<>()) // Initialize the list of imageUrls
                        .build();

                if (voteFindService.findVoteByNoticeId(p.noticeId()) != null) {
                    v2ListResponse.setVoteYn(true);
                }

                if (voteFindService.findVoteItemRecordByMemberId(request.memberId()) != null) {
                    v2ListResponse.setVoteAttendYn(true);
                }

                noticeMap.put(p.noticeId(), v2ListResponse);
            }

            // Add imageUrl to the list in case there are multiple values
            noticeMap.get(p.noticeId()).getImageUrl().add(p.imageUrl());
        }

        results.addAll(noticeMap.values());
        return results;
    }

    public List<CurriculumResponse> selectCurriculum() {

        List<CurriculumProjection> projection = noticeQueryService.selectCurriculum();
        return projection.stream().map(CurriculumMapper::toResponse).toList();
    }

    public List<CurriculumListResponse> selectCurriculumList(Long curriculumId) {

        List<Notice> entityResponse = noticeQueryService.selectCurriculumList(curriculumId);
        int size = entityResponse.size();

        List<CurriculumListResponse> response = noticeMapper.toCurriculumListDTO(entityResponse);

        for (int i = 0; i < size; i++) {
            response.get(i).setNoticeId(entityResponse.get(i).getId());
            response.get(i).setDate(convertDateString(entityResponse.get(i).getCreatedAt().toString()));
        }

        return response;
    }

    private LocalDate convertDateString(String dateString) {

        String datePart = dateString.substring(0, 10);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(datePart, formatter);
    }

}
