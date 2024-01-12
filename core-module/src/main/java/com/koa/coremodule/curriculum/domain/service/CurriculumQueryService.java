package com.koa.coremodule.curriculum.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.exception.CurriculumNotFoundException;
import com.koa.coremodule.curriculum.domain.repository.CurriculumRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import com.koa.commonmodule.exception.Error;

@DomainService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CurriculumQueryService {

    private final CurriculumRepository curriculumRepository;

    public Curriculum getRecentCurriculum() {
        LocalDate now = LocalDate.now();
        return curriculumRepository.findNearestCurriculum(now);
    }

    public List<Curriculum> getCurriculumList() {
        return curriculumRepository.findAll();
    }

    public Curriculum findCurriculumById(Long curriculumId) {
        return curriculumRepository.findById(curriculumId).orElseThrow(() -> new CurriculumNotFoundException(Error.CURRICULUM_NOT_FOUND));
    }
}
