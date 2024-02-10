package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.entity.Team;
import com.koa.coremodule.team.domain.exception.TeamError;
import com.koa.coremodule.team.domain.exception.TeamNotFoundException;
import com.koa.coremodule.team.domain.repository.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamQueryService {
    private final TeamRepository teamRepository;

    public List<Team> getTeamListByCurriculumId(Long curriculumId) {
        return teamRepository.findAllByCurriculumId(curriculumId);
    }

    public Team findTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(TeamError.TEAM_NOT_FOUND));
    }
}
