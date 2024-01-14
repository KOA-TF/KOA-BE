package com.koa.coremodule.curriculum.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.curriculum.application.dto.request.CurriculumCreateRequest;
import com.koa.coremodule.curriculum.application.mapper.CurriculumMapper;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.service.CurriculumSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class CurriculumCreateUseCase {

    private final CurriculumSaveService curriculumSaveService;

    public void createCurriculum(CurriculumCreateRequest curriculumCreateRequest) {
        Curriculum curriculum = CurriculumMapper.mapToCurriculum(curriculumCreateRequest);
        curriculumSaveService.saveCurriculum(curriculum);
    }
}
