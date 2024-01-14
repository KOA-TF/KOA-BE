package com.koa.apimodule.command.api;

import com.google.zxing.WriterException;
import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.attend.application.dto.AttendContent;
import com.koa.coremodule.attend.application.dto.AttendInfo;
import com.koa.coremodule.attend.application.dto.AttendList;
import com.koa.coremodule.attend.application.dto.AttendSaveRequest;
import com.koa.coremodule.attend.application.service.AttendGetUseCase;
import com.koa.coremodule.attend.application.service.AttendSaveUseCase;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.notice.application.service.NoticeGetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/attend")
public class AttendController {

    @Value("${qr.text}")
    private String QR_TEXT;

    private final AttendGetUseCase attendGetUseCase;
    private final AttendSaveUseCase attendSaveUseCase;
    private final NoticeGetUseCase noticeGetUseCase;

    /**
     * 출석 적재
     */
    @PostMapping
    public ApplicationResponse<Long> saveAttend(@RequestBody AttendSaveRequest attendSaveRequest) {

        Boolean checkText = attendGetUseCase.checkText(attendSaveRequest);
        if (checkText) {
            Long attendId = attendSaveUseCase.saveAttend(attendSaveRequest.curriculumId());
            return ApplicationResponse.ok(attendId, "출석을 정상적으로 완료하였습니다.");
        } else {
            return ApplicationResponse.badRequest(null, "출석 코드가 올바르지 않습니다.");
        }
    }

    /**
     * 출석 점수 현황 표기
     */
    @GetMapping
    public ApplicationResponse<AttendContent> getAttendStatus() {

        AttendContent result = attendGetUseCase.getAttendStatus();
        return ApplicationResponse.ok(result, "출석 현황을 성공적으로 조회했습니다.");
    }

    /**
     * 커리큘럼과 함께 리스트 전달
     */
    @GetMapping(value = "/lists")
    public ApplicationResponse<List<AttendList>> getAttendList() {

        List<AttendList> lists = attendGetUseCase.getAttendList();
        return ApplicationResponse.ok(lists, "커리큘럼에 따른 출석 리스트를 조회했습니다.");
    }

    /**
     * qr 코드 생성
     */
    @GetMapping(value = "/code", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ApplicationResponse<byte[]> generateQRCode(Long curriculumId) {

        Curriculum curriculum = noticeGetUseCase.findCurriculumById(curriculumId);

        String data = QR_TEXT + curriculum.getCurriculumName();
        int width = 300;
        int height = 300;

        try {
            BufferedImage bufferedImage = attendSaveUseCase.generateQRCodeImage(data, width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);

            return ApplicationResponse.ok(baos.toByteArray(), "qr 코드 이미지 생성 결과입니다.");
        } catch (WriterException | IOException e) {
            return ApplicationResponse.server(null, "qr 코드 생성 중 서버 에러입니다.");
        }
    }

    /**
     * qr text 생성
     */
    @GetMapping(value = "/text")
    public ApplicationResponse<String> generateQrText(Long curriculumId) {

        Curriculum curriculum = noticeGetUseCase.findCurriculumById(curriculumId);
        String data = QR_TEXT + curriculum.getCurriculumName();

        return ApplicationResponse.ok(data, "QR 텍스트 문구입니다.");
    }

    /**
     * 마감 버튼 클릭 시 일괄 결석 반영
     */
    @PostMapping(value = "/absent")
    public ApplicationResponse<Void> saveAllAbsent(Long curriculumId) {

        attendSaveUseCase.saveAllAbsent(curriculumId);
        return ApplicationResponse.ok(null, "일괄 결석 처리 되었습니다.");
    }

    /**
     * 다가오는 커리큘럼 정보 전달
     */
    @GetMapping(value = "/info")
    public ApplicationResponse<AttendInfo> getAttendInfo() {

        AttendInfo info = attendGetUseCase.getAttendInfo();
        return ApplicationResponse.ok(info, "이번주 출석 커리큘럼 정보입니다.");
    }
}
