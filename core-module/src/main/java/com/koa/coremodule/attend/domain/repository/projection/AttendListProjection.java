package com.koa.coremodule.attend.domain.repository.projection;

import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.koa.coremodule.attend.domain.entity.QAttend.attend;
import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;

@AllArgsConstructor
@Getter
public class AttendListProjection {

    public static final ConstructorExpression<AttendListProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(AttendListProjection.class,
                    attend.id,
                    attend.status,
                    attend.createdAt,
                    curriculum.date,
                    curriculum.curriculumName
            );

    private Long attendId;
    private AttendStatus status;
    private LocalDateTime attendTime;
    private String date;
    private String curriculumName;

}
