package com.koa.coremodule.attend.domain.repository.projection;

import static com.koa.coremodule.attend.domain.entity.QAttend.attend;
import static com.koa.coremodule.curriculum.domain.entity.QCurriculum.curriculum;

import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AttendListProjection {

    public static final ConstructorExpression<AttendListProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(AttendListProjection.class,
                    attend.id,
                    attend.status,
                    attend.createdAt,
                    curriculum.startTime,
                    curriculum.curriculumName
            );

    private Long attendId;
    private AttendStatus status;
    private LocalDateTime attendTime;
    private String date;
    private String curriculumName;

}
