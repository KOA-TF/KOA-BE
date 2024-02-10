package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.exception.AlreadyEnrollException;
import com.koa.coremodule.team.domain.exception.TeamError;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class EnrollValidateService {

    public void validateEnroll(List<Long> memberIds, Long memberId) {
        if (memberIds.contains(memberId)) {
            throw new AlreadyEnrollException(TeamError.ALREADY_ENROLL);
        }
    }
}
