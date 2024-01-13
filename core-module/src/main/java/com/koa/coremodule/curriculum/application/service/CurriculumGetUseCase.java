package com.koa.coremodule.curriculum.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.curriculum.application.dto.response.CurriculumInfoResponse;
import com.koa.coremodule.curriculum.application.dto.response.RecentCurriculumResponse;
import com.koa.coremodule.curriculum.application.mapper.CurriculumMapper;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.service.CurriculumQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CurriculumGetUseCase {

    private final CurriculumQueryService curriculumQueryService;

    public RecentCurriculumResponse getRecentCurriculum() {
        Curriculum recentCurriculum = curriculumQueryService.getRecentCurriculum();
        return CurriculumMapper.mapToRecentCurriculumResponse(recentCurriculum);
    }

    public List<CurriculumInfoResponse> getCurriculumList() {
        List<Curriculum> curriculumList = curriculumQueryService.getCurriculumList();
        List<CurriculumInfoResponse> curriculumInfoResponses = curriculumList.stream()
            .map(CurriculumMapper::mapToCurriculumInfoResponse)
            .toList();
        return curriculumInfoResponses;
    }
}
