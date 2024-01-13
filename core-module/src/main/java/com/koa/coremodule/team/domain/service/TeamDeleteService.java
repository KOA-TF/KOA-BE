package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TeamDeleteService {

    private final TeamRepository teamRepository;

    public void deleteTeamById(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
