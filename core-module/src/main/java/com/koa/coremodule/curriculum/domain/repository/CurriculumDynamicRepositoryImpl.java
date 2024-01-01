package com.koa.coremodule.curriculum.domain.repository;

import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.entity.QCurriculum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CurriculumDynamicRepositoryImpl implements CurriculumDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Curriculum findNearestCurriculum(LocalDate now) {
        final QCurriculum curriculum = QCurriculum.curriculum;

        // 현재 날짜와 같은 Curriculum이 있는지 검색
        Curriculum sameDateCurriculum = jpaQueryFactory.selectFrom(curriculum)
                .where(curriculum.startTime.between(now.atStartOfDay(), now.atTime(LocalTime.MAX)))
                .fetchFirst();

        // 같은 날짜의 Curriculum이 있다면 해당 엔터티를 반환
        if (sameDateCurriculum != null) {
            return sameDateCurriculum;
        }

        // 현재 날짜와 가장 가까운 미래의 Curriculum을 반환
        return jpaQueryFactory.selectFrom(curriculum)
                .where(curriculum.startTime.after(now.atStartOfDay()))
                .orderBy(curriculum.startTime.asc())
                .fetchFirst();
    }
}
