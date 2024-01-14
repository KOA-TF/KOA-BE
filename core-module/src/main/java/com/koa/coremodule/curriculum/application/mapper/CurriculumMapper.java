package com.koa.coremodule.curriculum.application.mapper;

import com.koa.coremodule.curriculum.application.dto.request.CurriculumCreateRequest;
import com.koa.coremodule.curriculum.application.dto.response.CurriculumInfoResponse;
import com.koa.coremodule.curriculum.application.dto.response.RecentCurriculumResponse;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurriculumMapper {

    public static Curriculum mapToCurriculum(CurriculumCreateRequest curriculumCreateRequest) {
        Curriculum curriculum = Curriculum.builder()
                .curriculumName(curriculumCreateRequest.curriculumName())
                .startTime(curriculumCreateRequest.startTime())
                .endTime(curriculumCreateRequest.endTime())
                .place(curriculumCreateRequest.place())
                .wifiName(curriculumCreateRequest.wifiName())
                .wifiPassword(curriculumCreateRequest.wifiPassword())
                .build();
        return curriculum;
    }

    public static RecentCurriculumResponse mapToRecentCurriculumResponse(Curriculum curriculum) {
        return new RecentCurriculumResponse(
                curriculum.getCurriculumName(),
                curriculum.getCurriculumTimeFormat(),
                curriculum.getPlace(),
                curriculum.getWifiName(),
                curriculum.getWifiPassword()
        );
    }

    public static CurriculumInfoResponse mapToCurriculumInfoResponse(Curriculum curriculum) {
        return new CurriculumInfoResponse(
                curriculum.getId(),
                curriculum.getCurriculumName()
        );
    }

}
