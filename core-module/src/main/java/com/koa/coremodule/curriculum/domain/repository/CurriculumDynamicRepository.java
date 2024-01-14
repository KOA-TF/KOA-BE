package com.koa.coremodule.curriculum.domain.repository;

import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import java.time.LocalDate;

public interface CurriculumDynamicRepository {
    Curriculum findNearestCurriculum(LocalDate now);
}
