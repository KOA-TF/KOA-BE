package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.repository.EnrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class EnrollDeleteService {

    private final EnrollRepository enrollRepository;

    public void deleteEnrollById(Long teamId, Long memberId) {
        enrollRepository.deleteByTeamIdAndMemberId(teamId, memberId);
    }

    public void deleteAllByTeamId(Long teamId) {
        enrollRepository.deleteAllByTeamId(teamId);
    }

}
