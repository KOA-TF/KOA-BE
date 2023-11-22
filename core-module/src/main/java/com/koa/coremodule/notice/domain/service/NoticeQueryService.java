package com.koa.coremodule.notice.domain.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.notice.application.dto.NoticeListResponse;
import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.application.dto.NoticeViewResponse;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.NoticeTeam;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.exception.NoticeNotFoundException;
import com.koa.coremodule.notice.domain.repository.CurriculumRepository;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import com.koa.coremodule.notice.domain.repository.NoticeTeamRepository;
import com.koa.coremodule.notice.domain.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeListProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeQueryService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final NoticeTeamRepository noticeTeamRepository;
    private final CurriculumRepository curriculumRepository;

    public List<NoticeListProjection> selectNotice() {

        List<NoticeListProjection> projection = noticeRepository.findAllNotice();

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

    public NoticeListProjection selectNoticeDetail(Long noticeId) {

        NoticeListProjection projection = noticeRepository.findAllNoticeDetail(noticeId);

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

    public Long findSingleViewId(Long noticeId, Long memberId) {
        return noticeRepository.findSingleViewId(memberId, noticeId);
    }

    public ViewType findSingleViewType(Long noticeId, Long memberId) {
        return noticeRepository.findSingleViewType(memberId, noticeId);
    }

    public void updateSingleViewYn(Long noticeViewId, Long memberId, ViewType viewType) {
        noticeRepository.updateSingleViewYn(noticeViewId, memberId, viewType);
    }

    public Optional<Member> findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<NoticeTeam> findNoticeTeamById(Long noticeTeamId) {
        return noticeTeamRepository.findById(noticeTeamId);
    }

    public Optional<Curriculum> findCurriculumById(Long curriculumId) {
        return curriculumRepository.findById(curriculumId);
    }

}
