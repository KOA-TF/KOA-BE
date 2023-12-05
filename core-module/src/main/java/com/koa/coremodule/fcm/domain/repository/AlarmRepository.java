package com.koa.coremodule.fcm.domain.repository;

import com.koa.coremodule.fcm.domain.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    void deleteAlarmById(Long alarmId);

}
