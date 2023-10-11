package com.koa.apimodule.command.api.notice.service;

import com.koa.commonmodule.testisolation.TestIsolation;
import com.koa.coremodule.notice.application.dto.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/notice_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class NoticeFindUseCaseTest {

    private static final Long MEMBER_ID = 1L;

    @Autowired
    private NoticeFindUseCase noticeFindUseCase;

    @Nested
    @DisplayName("공지사항 조회")
    class FindNoticeTest {

        @Test
        void 정상적으로_조회한다() {

            //given
            NoticeListRequest request = new NoticeListRequest(MEMBER_ID);

            //when
            List<NoticeListResponse> response = noticeFindUseCase.selectNotice(request);

            //then
            Assertions.assertThat(response.get(0).noticeId()).isEqualTo(1L);
            Assertions.assertThat(response.get(0).title()).isEqualTo("제목입니다.");

        }

        @Test
        void selectCurriculum() {

            //given

            //when
            List<CurriculumResponse> response = noticeFindUseCase.selectCurriculum();

            //then
            System.out.println(response);

        }

        @Test
        void selectCurriculumList() {

            //given
            CurriculumListRequest request = new CurriculumListRequest(1L);

            //when
            List<CurriculumListResponse> response = noticeFindUseCase.selectCurriculumList(request);

            //then
            Assertions.assertThat(response.get(0).title()).isEqualTo("제목입니다.");

        }

    }
}