package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.entity.Enroll;
import com.koa.coremodule.team.domain.repository.EnrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class EnrollSaveService {

    private final EnrollRepository enrollRepository;

    public void saveEnroll(Enroll enroll) {
        enrollRepository.save(enroll);
    }

}
