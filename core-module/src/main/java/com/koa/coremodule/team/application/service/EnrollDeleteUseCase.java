package com.koa.coremodule.team.application.service;


import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.team.domain.service.EnrollDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class EnrollDeleteUseCase {

    private final EnrollDeleteService enrollDeleteService;

    public void deleteEnroll(Long teamId, Long memberId) {
        enrollDeleteService.deleteEnrollById(teamId, memberId);
    }

}
