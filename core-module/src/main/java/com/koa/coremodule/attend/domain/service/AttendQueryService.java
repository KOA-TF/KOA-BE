package com.koa.coremodule.attend.domain.service;

import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.koa.coremodule.attend.domain.repository.AttendRepository;
import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendQueryService {

    private final AttendRepository attendRepository;

    public Map<AttendStatus, Integer> getAttendStatus(Long memberId) {

        List<Object[]> list = attendRepository.findAttendStatusCountByMemberId(memberId);

        Map<AttendStatus, Integer> statusMap = new HashMap<>();
            for (Object[] result : list) {
            AttendStatus status = (AttendStatus) result[0];
            Integer count = ((Number) result[1]).intValue();
            statusMap.put(status, count);
        }

        return statusMap;
    }

    public List<AttendListProjection> getAttendList(Long memberId) {

        return attendRepository.getAttendList(memberId);
    }

}
