package com.koa.apimodule.command.api.notice.service;

import com.koa.commonmodule.testisolation.TestIsolation;
import com.koa.coremodule.notice.application.dto.NoticeDetailResponse;
import com.koa.coremodule.notice.application.dto.NoticeRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/notice_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class NoticeSaveUseCaseTest {

    private static final Long MEMBER_ID = 1L;
    private static final Long CURRICULUM_ID = 1L;
    private static final Long TEAM_ID = 1L;
    private static final Long NOTICE_ID = 1L;

    @Autowired
    private NoticeSaveUseCase noticeSaveUseCase;

    @Nested
    @DisplayName("공지사항 저장 및 상세 조회")
    class SaveNoticeTest {

        @Test
        void saveNotice() {

            //given
            byte[] content = "dummy content".getBytes();
            MultipartFile file = new MockMultipartFile("testFile.jpg", "testFile.jpg", "image/jpeg", content);
            NoticeRequest noticeRequest = new NoticeRequest(MEMBER_ID, TEAM_ID, CURRICULUM_ID, null, "제목입니다.", "내용입니다.");

            //when
            Long response = noticeSaveUseCase.saveNotice(noticeRequest, file);

            //then
            Assertions.assertThat(response).isEqualTo(3L);

        }

        @Test
        void selectNoticeDetail() {

            //given

            //when
            NoticeDetailResponse response = noticeSaveUseCase.selectNoticeDetail(NOTICE_ID);

            //then
            System.out.println(response);

        }

    }

}
