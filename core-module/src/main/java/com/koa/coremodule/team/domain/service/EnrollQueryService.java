package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.entity.Enroll;
import com.koa.coremodule.team.domain.repository.EnrollRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnrollQueryService {
    private final EnrollRepository enrollRepository;

    public List<Enroll> findAll() {
        return enrollRepository.findAll();
    }

    public List<Enroll> getAllEnrollByMemberId(Long memberId) {
        return enrollRepository.findAllByMemberId(memberId);
    }
}
