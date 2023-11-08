package com.koa.apimodule.command.api.notice.service;

import com.koa.commonmodule.testisolation.TestIsolation;
import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.application.service.NoticeFindUseCase;
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
    private static final Long CURRICULUM_ID = 1L;

    @Autowired
    private NoticeFindUseCase noticeFindUseCase;

    @Nested
    @DisplayName("공지사항 조회")
    class FindNoticeTest {

        @Test
        void selectNotice() {

            //given

            //when
            List<NoticeListResponse> response = noticeFindUseCase.selectNotice(MEMBER_ID);

            //then
            System.out.println("response = " + response);
            Assertions.assertThat(response.get(0).noticeId()).isEqualTo(1L);
            Assertions.assertThat(response.get(0).title()).isEqualTo("제목입니다.");
            Assertions.assertThat(response.get(0).imageUrl()).isEqualTo("image.png");
        }

        @Test
        void selectCurriculum() {

            //given

            //when
            List<CurriculumResponse> response = noticeFindUseCase.selectCurriculum();

            //then
            Assertions.assertThat(response.size()).isEqualTo(2);

        }

        @Test
        void selectCurriculumList() {

            //given

            //when
            List<CurriculumListResponse> response = noticeFindUseCase.selectCurriculumList(CURRICULUM_ID);

            //then
            Assertions.assertThat(response.get(0).title()).isEqualTo("제목입니다.");

        }

    }
}
