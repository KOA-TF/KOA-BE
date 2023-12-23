package com.koa.coremodule.attend.domain.service;

import com.koa.coremodule.attend.domain.entity.Attend;
import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.koa.coremodule.attend.domain.repository.AttendRepository;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendSaveService {

    private final AttendRepository attendRepository;
    private final NoticeQueryService noticeQueryService;

    public Attend saveAttend(Long curriculumId, Long memberId) {

        Member member = noticeQueryService.findMemberById(memberId);
        Curriculum curriculum = noticeQueryService.findCurriculumById(curriculumId);
        final Attend attend = Attend.builder().curriculum(curriculum).member(member).status(AttendStatus.PRESENT).build();

        return attendRepository.save(attend);
    }

    public Attend saveAttendForLate(Long curriculumId, Long memberId) {

        Member member = noticeQueryService.findMemberById(memberId);
        Curriculum curriculum = noticeQueryService.findCurriculumById(curriculumId);
        final Attend attend = Attend.builder().curriculum(curriculum).member(member).status(AttendStatus.LATE).build();

        return attendRepository.save(attend);
    }

}
