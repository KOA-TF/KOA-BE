package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
