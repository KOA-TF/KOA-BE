package com.koa.coremodule.fcm.domain.service;

import com.koa.coremodule.fcm.domain.entity.Alarm;
import com.koa.coremodule.fcm.domain.entity.AlarmView;
import com.koa.coremodule.fcm.domain.exception.AlarmError;
import com.koa.coremodule.fcm.domain.exception.AlarmNotFoundException;
import com.koa.coremodule.fcm.domain.exception.DuplicateAlarmException;
import com.koa.coremodule.fcm.domain.repository.AlarmRepository;
import com.koa.coremodule.fcm.domain.repository.AlarmViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmQueryService {

    private final AlarmRepository alarmRepository;
    private final AlarmViewRepository alarmViewRepository;

    public List<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    public Alarm findAlarmById(Long alarmId) {
        return alarmRepository.findById(alarmId)
                .orElseThrow(() -> new AlarmNotFoundException(AlarmError.ALARM_NOT_FOUND));
    }

    public List<AlarmView> findViews(Long memberId) {
        return alarmViewRepository.findAllByMemberId(memberId);
    }

    public void existsByAlarmIdAndMemberId(Long alarmId, Long memberId) {
        if(alarmRepository.existsByAlarmIdAndMemberId(alarmId, memberId))
            throw new DuplicateAlarmException(AlarmError.DUPLICATE_ALARM);
    }

}
