package com.koa.coremodule.fcm.domain.repository;

import com.koa.coremodule.fcm.domain.entity.AlarmView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmViewRepository extends JpaRepository<AlarmView, Long> {

    List<AlarmView> findAllByMemberId(Long memberId);

    void deleteAlarmViewByAlarmId(Long alarmId);

}
