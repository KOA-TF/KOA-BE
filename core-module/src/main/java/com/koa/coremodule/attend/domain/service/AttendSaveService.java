package com.koa.coremodule.attend.domain.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.attend.domain.entity.Attend;
import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.koa.coremodule.attend.domain.exception.AttendException;
import com.koa.coremodule.attend.domain.repository.AttendRepository;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.service.CurriculumQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendSaveService {

    private final AttendRepository attendRepository;
    private final CurriculumQueryService curriculumQueryService;
    private final MemberQueryService memberQueryService;

    public Attend saveAttend(Long curriculumId, Long memberId) {

        if (attendRepository.existsByCurriculumIdAndMemberId(curriculumId, memberId)) {
            throw new AttendException(Error.DUPLICATE_ATTEND);
        }

        Member member = memberQueryService.findMemberById(memberId);
        Curriculum curriculum = curriculumQueryService.findCurriculumById(curriculumId);

        LocalDateTime currentTime = LocalDateTime.now();
        final Attend attend;

        if (currentTime.isAfter(curriculum.getStartTime())) {
            attend = Attend.builder()
                    .curriculum(curriculum)
                    .member(member)
                    .status(AttendStatus.지각)
                    .build();
        } else {
            attend = Attend.builder()
                    .curriculum(curriculum)
                    .member(member)
                    .status(AttendStatus.출석)
                    .build();
        }

        return attendRepository.save(attend);
    }

    public Attend saveAttendForLate(Long curriculumId, Long memberId) {

        Member member = memberQueryService.findMemberById(memberId);
        Curriculum curriculum = curriculumQueryService.findCurriculumById(curriculumId);
        final Attend attend = Attend.builder().curriculum(curriculum).member(member).status(AttendStatus.결석).build();

        return attendRepository.save(attend);
    }

}
