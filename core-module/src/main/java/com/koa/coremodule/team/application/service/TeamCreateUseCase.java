package com.koa.coremodule.team.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.service.CurriculumQueryService;
import com.koa.coremodule.team.application.dto.request.TeamCreateRequest;
import com.koa.coremodule.team.application.mapper.TeamMapper;
import com.koa.coremodule.team.domain.entity.Team;
import com.koa.coremodule.team.domain.service.TeamSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TeamCreateUseCase {

    private final TeamSaveService teamSaveService;
    private final CurriculumQueryService curriculumQueryService;

    public void createTeam(Long curriculumId, TeamCreateRequest teamCreateRequest) {
        Curriculum curriculum = curriculumQueryService.findCurriculumById(curriculumId);
        Team team = TeamMapper.mapToTeamEntity(curriculum, teamCreateRequest);
        teamSaveService.saveTeam(team);
    }
}
