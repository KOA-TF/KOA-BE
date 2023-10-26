package com.koa.apimodule.command.api.report.service;

import com.koa.commonmodule.testisolation.TestIsolation;
import com.koa.coremodule.report.application.dto.ReportRequest;
import com.koa.coremodule.report.application.service.ReportSaveUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/report_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ReportSaveUseCaseTest {

    @Autowired
    private ReportSaveUseCase reportSaveUseCase;

    @Nested
    @DisplayName("신고 작성")
    class ReportTest {

        @Test
        void createReport() {

            //given
            ReportRequest reportRequest = ReportRequest.builder()
                    .memberId(1L)
                    .commentId(1L)
                    .content("신고 내용입니다.").build();

            //when
            Long result = reportSaveUseCase.createReport(reportRequest);

            //then
            System.out.println(result);
        }

    }

}
