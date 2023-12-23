package com.koa.coremodule.notice.domain.service;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.notice.application.dto.NoticeListResponse;
import com.koa.coremodule.notice.application.dto.NoticeSelectRequest;
import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.*;
import com.koa.coremodule.notice.domain.exception.NoticeException;
import com.koa.coremodule.notice.domain.exception.NoticeNotFoundException;
import com.koa.coremodule.notice.domain.repository.*;
import com.koa.coremodule.notice.domain.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeDetailListProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeListProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeV2DetailListProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeQueryService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final NoticeTeamRepository noticeTeamRepository;
    private final CurriculumRepository curriculumRepository;
    private final NoticeViewRepository noticeViewRepository;
    private final NoticeImageRepository noticeImageRepository;

    public List<NoticeListProjection> selectNotice() {

        List<NoticeListProjection> projection = noticeRepository.findAllNotice();

        return projection;
    }

    public List<NoticeListProjection> selectNoticeV2(NoticeSelectRequest request) {

        List<NoticeListProjection> projection = noticeRepository.findAllNoticeV2(request);

        return projection;
    }

    public void findViewYn(List<NoticeListResponse> response, Long memberId, List<NoticeListProjection> projection) {

        int i = 0;
        while (i < response.size()) {

            NoticeViewRequest viewRequest = new NoticeViewRequest(memberId, projection.get(i).getNoticeId());
            ViewType viewType = noticeRepository.findViewYn(viewRequest);

            if (viewType != null) {

                if (viewType.equals(ViewType.VIEWED)) {
                    NoticeListResponse originalResponse = response.get(i);
                    NoticeListResponse updatedResponse = originalResponse.withViewYn(true);
                    response.set(i, updatedResponse);
                }
            } else {
                final Member member = findMemberById(memberId);
                final Notice noticeEntity = noticeRepository.findById(viewRequest.noticeId()).orElseThrow(() -> new NoticeNotFoundException(Error.NOTICE_NOT_FOUND));
                NoticeView noticeView = NoticeView.create(ViewType.NONE, member, noticeEntity);
                noticeViewRepository.save(noticeView);
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

    public NoticeImage saveImage(NoticeImage noticeImage) {

        return noticeImageRepository.save(noticeImage);
    }

    public NoticeDetailListProjection selectNoticeDetail(Long noticeId) {

        NoticeDetailListProjection projection = noticeRepository.findAllNoticeDetail(noticeId);

        return projection;
    }

    public NoticeV2DetailListProjection selectNoticeDetailV2(Long noticeId) {

        NoticeV2DetailListProjection projection = noticeRepository.findAllNoticeV2Detail(noticeId);

        return projection;
    }

    public Notice findByNoticeId(Long noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow(() -> new NoticeNotFoundException(Error.NOTICE_NOT_FOUND));
    }

    public List<Long> findNoticeIdsByMemberId(Long memberId) {
        return noticeRepository.findIdsByMemberId(memberId);
    }

    public String findImageByNoticeId(Long noticeId) {
        return noticeRepository.findImageByNoticeId(noticeId);
    }

    public List<String> findImagesByNoticeId(Long noticeId) {
        return noticeRepository.findImagesByNoticeId(noticeId);
    }

    public Long findSingleViewId(Long noticeId, Long memberId) {
        return noticeRepository.findSingleViewId(memberId, noticeId);
    }

    public ViewType findSingleViewType(Long noticeId, Long memberId) {
        return noticeRepository.findSingleViewType(memberId, noticeId);
    }

    public void updateSingleViewYn(Long noticeViewId, Long memberId, ViewType viewType) {
        noticeRepository.updateSingleViewYn(noticeViewId, memberId, viewType);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(Error.MEMBER_NOT_FOUND));
    }

    public NoticeTeam findNoticeTeamById(Long noticeTeamId) {
        return noticeTeamRepository.findById(noticeTeamId).orElseThrow(() -> new NoticeException(Error.NOTICE_TEAM_NOT_FOUND));
    }

    public Curriculum findCurriculumById(Long curriculumId) {
        return curriculumRepository.findById(curriculumId).orElseThrow(() -> new NoticeException(Error.CURRICULUM_NOT_FOUND));
    }

}
