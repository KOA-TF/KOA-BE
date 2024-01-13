package com.koa.coremodule.attend.domain.service;

import com.koa.coremodule.attend.domain.entity.Attend;
import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.koa.coremodule.attend.domain.repository.AttendRepository;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.service.CurriculumQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendSaveService {

    private final AttendRepository attendRepository;
    private final CurriculumQueryService curriculumQueryService;
    private final MemberQueryService memberQueryService;

    public Attend saveAttend(Long curriculumId, Long memberId) {

        Member member = memberQueryService.findMemberById(memberId);
        Curriculum curriculum = curriculumQueryService.findCurriculumById(curriculumId);
        final Attend attend = Attend.builder().curriculum(curriculum).member(member).status(AttendStatus.PRESENT).build();

        return attendRepository.save(attend);
    }

    public Attend saveAttendForLate(Long curriculumId, Long memberId) {

        Member member = memberQueryService.findMemberById(memberId);
        Curriculum curriculum = curriculumQueryService.findCurriculumById(curriculumId);
        final Attend attend = Attend.builder().curriculum(curriculum).member(member).status(AttendStatus.LATE).build();

        return attendRepository.save(attend);
    }

}
