package com.koa.coremodule.team.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.team.application.dto.response.TeamMatchResponse;
import com.koa.coremodule.team.application.dto.response.TeamInfoResponse;
import com.koa.coremodule.team.application.mapper.TeamMapper;
import com.koa.coremodule.team.domain.entity.Enroll;
import com.koa.coremodule.team.domain.entity.Team;
import com.koa.coremodule.team.domain.service.EnrollQueryService;
import com.koa.coremodule.team.domain.service.TeamQueryService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TeamGetUseCase {

    private final TeamQueryService teamQueryService;
    private final EnrollQueryService enrollQueryService;
    private final MemberUtils memberUtils;

    public List<TeamInfoResponse> getTeamListByCurriculumId(Long curriculumId) {
        List<Team> teamList = teamQueryService.getTeamListByCurriculumId(curriculumId);
        Map<Team, List<Enroll>> enrollmentMap = createTeamEnrollmentMap();

        List<TeamInfoResponse> teamInfoResponses = teamList.stream()
                .map(team -> TeamMapper.mapToTeamInfoResponse(team, enrollmentMap.getOrDefault(team, Collections.emptyList()).size()))
                .collect(Collectors.toList());

        return teamInfoResponses;
    }

    private Map<Team, List<Enroll>> createTeamEnrollmentMap() {
        List<Enroll> enrollList = enrollQueryService.findAllWithTeam();
        Map<Team, List<Enroll>> enrollMap = enrollList.stream()
            .collect(Collectors.groupingBy(Enroll::getTeam));
        return enrollMap;
    }

    public List<TeamMatchResponse> getMatchTeamList() {
        Member member = memberUtils.getAccessMember();
        List<Enroll> enrolls = enrollQueryService.getAllEnrollByMemberId(member.getId());
        List<TeamMatchResponse> teamMatchResponses = enrolls.stream()
            .map(enroll -> TeamMapper.mapToTeamMatchResponse(enroll.getTeam(), enroll.getTeam().getCurriculum()))
            .toList();
        return teamMatchResponses;
    }

}
