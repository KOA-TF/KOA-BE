package com.koa.coremodule.team.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.team.domain.exception.AlreadyEnrollException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.koa.commonmodule.exception.Error;

@DomainService
@RequiredArgsConstructor
public class EnrollValidateService {

    public void validateEnroll(List<Long> memberIds, Long memberId) {
        if (memberIds.contains(memberId)) {
            throw new AlreadyEnrollException(Error.ALREADY_ENROLL);
        }
    }
}
