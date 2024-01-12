package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.entity.Enrollment;
import com.koa.coremodule.team.domain.repository.EnrollmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnrollmentQueryService {
    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }
}
