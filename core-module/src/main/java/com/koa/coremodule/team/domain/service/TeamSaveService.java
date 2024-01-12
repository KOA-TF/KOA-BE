package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.entity.Team;
import com.koa.coremodule.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TeamSaveService {

    private final TeamRepository teamRepository;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }
}
