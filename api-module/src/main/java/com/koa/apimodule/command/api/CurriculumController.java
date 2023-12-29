package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.curriculum.application.dto.request.CurriculumCreateRequest;
import com.koa.coremodule.curriculum.application.service.CurriculumCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/curriculum")
public class CurriculumController {

    private final CurriculumCreateUseCase curriculumCreateUseCase;

    @PostMapping("/create")
    public ApplicationResponse<Void> createCurriculum(@RequestBody CurriculumCreateRequest curriculumCreateRequest) {
        curriculumCreateUseCase.createCurriculum(curriculumCreateRequest);
        return ApplicationResponse.ok(null);
    }

}
