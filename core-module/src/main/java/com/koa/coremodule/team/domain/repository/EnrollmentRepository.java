package com.koa.coremodule.team.domain.repository;

import com.koa.coremodule.team.domain.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
