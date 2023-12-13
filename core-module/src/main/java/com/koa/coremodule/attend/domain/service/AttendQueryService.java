package com.koa.coremodule.attend.domain.service;

import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.koa.coremodule.attend.domain.repository.AttendRepository;
import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;
import com.koa.coremodule.attend.domain.repository.projection.AttendStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendQueryService {

    private final AttendRepository attendRepository;

    public Map<AttendStatus, Long> getAttendStatus(Long memberId) {
        List<AttendStatusProjection> list = attendRepository.findAttendStatusCountByMemberId(memberId);

        Map<AttendStatus, Long> statusMap = list.stream()
                .collect(Collectors.toMap(
                        AttendStatusProjection::getStatus,
                        AttendStatusProjection::getCount
                ));

        return statusMap;
    }

    public List<AttendListProjection> getAttendList(Long memberId) {

        return attendRepository.getAttendList(memberId);
    }

}
