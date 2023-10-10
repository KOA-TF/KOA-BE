package com.koa.apimodule.command.api.notice.service;

import com.koa.commonmodule.testisolation.TestIsolation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/notice_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class NoticeFindUseCaseTest {

    @Autowired
    private NoticeFindUseCase noticeFindUseCase;

    @Test
    void selectNotice() {
    }

    @Test
    void selectCurriculum() {
    }

    @Test
    void selectCurriculumList() {
    }
}