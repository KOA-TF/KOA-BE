package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.team.application.dto.request.EnrollCreateRequest;
import com.koa.coremodule.team.application.service.EnrollCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/enroll")
public class EnrollController {

    private final EnrollCreateUseCase enrollCreateUseCase;

    @PostMapping("/{teamId}")
    public ApplicationResponse<Void> postEnroll(@PathVariable Long teamId, @RequestBody EnrollCreateRequest enrollCreateRequest) {
        enrollCreateUseCase.createEnroll(teamId, enrollCreateRequest);
        return ApplicationResponse.ok(null);
    }
}
