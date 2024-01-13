package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.curriculum.application.dto.request.CurriculumCreateRequest;
import com.koa.coremodule.curriculum.application.dto.response.CurriculumInfoResponse;
import com.koa.coremodule.curriculum.application.dto.response.RecentCurriculumResponse;
import com.koa.coremodule.curriculum.application.service.CurriculumCreateUseCase;
import com.koa.coremodule.curriculum.application.service.CurriculumGetUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final CurriculumGetUseCase curriculumGetUseCase;

    @PostMapping("/create")
    public ApplicationResponse<Void> createCurriculum(@RequestBody CurriculumCreateRequest curriculumCreateRequest) {
        curriculumCreateUseCase.createCurriculum(curriculumCreateRequest);
        return ApplicationResponse.ok(null);
    }

    @GetMapping("/recent")
    public ApplicationResponse<RecentCurriculumResponse> getRecentCurriculum() {
        RecentCurriculumResponse response = curriculumGetUseCase.getRecentCurriculum();
        return ApplicationResponse.ok(response);
    }

    @GetMapping
    public ApplicationResponse<List<CurriculumInfoResponse>> getCurriculumList() {
        List<CurriculumInfoResponse> response = curriculumGetUseCase.getCurriculumList();
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/match")
    public ApplicationResponse<List<CurriculumInfoResponse>> getMatchCurriculumList() {
        List<CurriculumInfoResponse> response = curriculumGetUseCase.getMatchCurriculumList();
        return ApplicationResponse.ok(response);
    }

}
