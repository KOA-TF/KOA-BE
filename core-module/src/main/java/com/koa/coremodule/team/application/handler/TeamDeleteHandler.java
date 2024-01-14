package com.koa.coremodule.team.application.handler;

import com.koa.coremodule.team.application.handler.event.TeamDeleteEvent;
import com.koa.coremodule.team.domain.service.EnrollDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class TeamDeleteHandler {
    private final EnrollDeleteService enrollDeleteService;

    @EventListener
    public void deleteEnroll(TeamDeleteEvent teamDeleteEvent) {
        enrollDeleteService.deleteAllByTeamId(teamDeleteEvent.teamId());
    }
}
