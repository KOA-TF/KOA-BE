package com.koa.coremodule.team.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.team.application.dto.request.TeamNameChangeRequset;
import com.koa.coremodule.team.domain.entity.Team;
import com.koa.coremodule.team.domain.service.TeamQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TeamChangeUseCase {

    private final TeamQueryService teamQueryService;

    public void changeTeamName(Long teamId, TeamNameChangeRequset teamNameChangeRequset) {
        Team team = teamQueryService.findTeamById(teamId);
        team.updateTeamName(teamNameChangeRequset.teamName());
    }

}
