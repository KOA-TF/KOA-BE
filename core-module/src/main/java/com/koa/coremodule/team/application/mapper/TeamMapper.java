package com.koa.coremodule.team.application.mapper;

import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.team.application.dto.request.TeamCreateRequest;
import com.koa.coremodule.team.application.dto.response.TeamMatchResponse;
import com.koa.coremodule.team.application.dto.response.TeamInfoResponse;
import com.koa.coremodule.team.domain.entity.Team;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamMapper {

    public static TeamInfoResponse mapToTeamInfoResponse(Team team, Integer enrollmentCount) {
        return new TeamInfoResponse(
                team.getId(),
                team.getTeamName(),
                enrollmentCount
        );
    }

    public static TeamMatchResponse mapToTeamMatchResponse(Team team, Curriculum curriculum) {
        return new TeamMatchResponse(
                curriculum.getCurriculumName(),
                team.getId()
        );
    }

    public static Team mapToTeamEntity(Curriculum curriculum, TeamCreateRequest teamCreateRequest) {
        return Team.builder()
                .curriculum(curriculum)
                .teamName(teamCreateRequest.teamName())
                .build();
    }

}
