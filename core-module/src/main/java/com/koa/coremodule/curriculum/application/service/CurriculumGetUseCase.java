package com.koa.coremodule.curriculum.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.curriculum.application.dto.response.CurriculumInfoResponse;
import com.koa.coremodule.curriculum.application.dto.response.RecentCurriculumResponse;
import com.koa.coremodule.curriculum.application.mapper.CurriculumMapper;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.service.CurriculumQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.team.domain.entity.Enroll;
import com.koa.coremodule.team.domain.service.EnrollQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CurriculumGetUseCase {

    private final CurriculumQueryService curriculumQueryService;
    private final EnrollQueryService enrollQueryService;
    private final MemberUtils memberUtils;

    public RecentCurriculumResponse getRecentCurriculum() {
        Curriculum recentCurriculum = curriculumQueryService.getRecentCurriculum();
        return CurriculumMapper.mapToRecentCurriculumResponse(recentCurriculum);
    }

    public List<CurriculumInfoResponse> getCurriculumList() {
        List<Curriculum> curriculumList = curriculumQueryService.getCurriculumList();
        List<CurriculumInfoResponse> curriculumInfoResponses = curriculumList.stream()
            .map(CurriculumMapper::mapToCurriculumInfoResponse)
            .toList();
        return curriculumInfoResponses;
    }

    public List<CurriculumInfoResponse> getMatchCurriculumList() {
        Member member = memberUtils.getAccessMember();
        List<Enroll> enrolls = enrollQueryService.getAllEnrollByMemberId(member.getId());
        List<CurriculumInfoResponse> curriculumInfoResponses = enrolls.stream()
            .map(enroll -> CurriculumMapper.mapToCurriculumInfoResponse(enroll.getTeam().getCurriculum()))
            .toList();
        return curriculumInfoResponses;
    }
}
