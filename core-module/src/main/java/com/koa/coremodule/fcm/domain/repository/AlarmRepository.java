package com.koa.coremodule.fcm.domain.repository;

import com.koa.coremodule.fcm.domain.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    void deleteAlarmById(Long alarmId);

    @Query("SELECT COUNT(a) > 0 FROM Alarm a WHERE a.id = :alarmId AND a.member.id = :memberId")
    boolean existsByAlarmIdAndMemberId(@Param("alarmId") Long alarmId, @Param("memberId") Long memberId);

}
