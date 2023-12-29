package com.koa.coremodule.curriculum.domain.repository;

import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
