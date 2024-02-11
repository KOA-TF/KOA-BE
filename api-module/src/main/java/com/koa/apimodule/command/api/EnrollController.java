package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.team.application.service.EnrollCreateUseCase;
import com.koa.coremodule.team.application.service.EnrollDeleteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/enroll")
public class EnrollController {

    private final EnrollCreateUseCase enrollCreateUseCase;
    private final EnrollDeleteUseCase enrollDeleteUseCase;

    @PostMapping("/{teamId}/{memberId}")
    public ApplicationResponse<Void> postEnroll(@PathVariable Long teamId, @PathVariable Long memberId) {
        enrollCreateUseCase.createEnroll(teamId, memberId);
        return ApplicationResponse.ok(null);
    }

    @DeleteMapping("/{teamId}/{memberId}")
    public ApplicationResponse<Void> deleteEnroll(@PathVariable Long teamId, @PathVariable Long memberId) {
        enrollDeleteUseCase.deleteEnroll(teamId, memberId);
        return ApplicationResponse.ok(null);
    }
}
