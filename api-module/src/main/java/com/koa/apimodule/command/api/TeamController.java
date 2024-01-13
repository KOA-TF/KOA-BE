package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.team.application.dto.request.TeamCreateRequest;
import com.koa.coremodule.team.application.dto.request.TeamNameChangeRequset;
import com.koa.coremodule.team.application.dto.response.TeamInfoResponse;
import com.koa.coremodule.team.application.service.TeamChangeUseCase;
import com.koa.coremodule.team.application.service.TeamCreateUseCase;
import com.koa.coremodule.team.application.service.TeamGetUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/team")
public class TeamController {

    private final TeamGetUseCase teamGetUseCase;
    private final TeamChangeUseCase teamChangeUseCase;
    private final TeamCreateUseCase teamCreateUseCase;

    @GetMapping("/{curriculumId}")
    public ApplicationResponse<List<TeamInfoResponse>> getTeamList(@PathVariable Long curriculumId) {
        List<TeamInfoResponse> response = teamGetUseCase.getTeamListByCurriculumId(curriculumId);
        return ApplicationResponse.ok(response);
    }

    @PutMapping("/{teamId}")
    public ApplicationResponse<Void> putTeam(@PathVariable Long teamId, @RequestBody TeamNameChangeRequset teamNameChangeRequset) {
        teamChangeUseCase.changeTeamName(teamId, teamNameChangeRequset);
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/{curriculumId}")
    public ApplicationResponse<Void> postTeam(@PathVariable Long curriculumId, @RequestBody TeamCreateRequest teamCreateRequest) {
        teamCreateUseCase.createTeam(curriculumId, teamCreateRequest);
        return ApplicationResponse.ok(null);
    }
}
