package com.koa.coremodule.curriculum.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.repository.CurriculumRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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


}
