package com.koa.coremodule.team.application.mapper;

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
}
