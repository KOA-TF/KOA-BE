package com.koa.coremodule.team.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.team.application.handler.event.TeamDeleteEvent;
import com.koa.coremodule.team.domain.service.TeamDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TeamDeleteUseCase {

    private final TeamDeleteService teamDeleteService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void deleteTeam(Long teamId) {
        teamDeleteService.deleteTeamById(teamId);
        applicationEventPublisher.publishEvent(new TeamDeleteEvent(teamId));
    }
}
