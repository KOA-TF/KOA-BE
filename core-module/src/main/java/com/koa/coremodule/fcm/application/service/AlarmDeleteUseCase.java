package com.koa.coremodule.fcm.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.fcm.domain.service.AlarmDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class AlarmDeleteUseCase {

    private final AlarmDeleteService alarmDeleteService;

    public void deleteAlarm(Long alarmId) {
        alarmDeleteService.deleteAlarm(alarmId);
    }
}
