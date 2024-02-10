package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.fcm.application.dto.AlarmLists;
import com.koa.coremodule.fcm.application.service.AlarmDeleteUseCase;
import com.koa.coremodule.fcm.application.service.AlarmUseCase;
import com.koa.coremodule.notice.application.dto.fcm.RegisterTokenRequest;
import com.koa.coremodule.notice.application.dto.fcm.SendCommentNotificationRequest;
import com.koa.coremodule.notice.application.dto.fcm.SendNoticeNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/alarm")
public class AlarmController {

    private final AlarmUseCase alarmUseCase;
    private final AlarmDeleteUseCase alarmDeleteUseCase;

    /**
     * 토큰 등록
     */
    @PostMapping("/register")
    public ApplicationResponse<Void> registerToken(@RequestBody RegisterTokenRequest request) {

        alarmUseCase.registerFcmToken(request.fcmToken());
        return ApplicationResponse.ok(null, "성공적으로 토큰이 등록되었습니다.");
    }

    /**
     * 메세지 전달 ( 공지 알림 )
     */
    @PostMapping("/send/notice")
    public ApplicationResponse<Void> sendNoticeNotification(@RequestBody SendNoticeNotificationRequest request) {

        alarmUseCase.sendNoticeNotification(request);
        return ApplicationResponse.ok(null, "성공적으로 공지 알림을 보냈습니다.");
    }

    /**
     * 메세지 전달 ( 댓글 알림 )
     */
    @PostMapping("/send/comment")
    public ApplicationResponse<Void> sendCommentNotification(@RequestBody SendCommentNotificationRequest request) {

        alarmUseCase.sendCommentNotification(request);
        return ApplicationResponse.ok(null, "성공적으로 댓글 알림을 보냈습니다.");
    }

    /**
     * 메세지 전달 ( 대댓글 알림 )
     */
    @PostMapping("/send/recomment")
    public ApplicationResponse<Void> sendReCommentNotification(@RequestBody SendCommentNotificationRequest request) {

        alarmUseCase.sendReCommentNotification(request);
        return ApplicationResponse.ok(null, "성공적으로 대댓글 알림을 보냈습니다.");
    }


    /**
     * 알림 목록 조회 ( 조회 여부까지 포함 )
     */
    @GetMapping("/lists")
    public ApplicationResponse<List<AlarmLists>> getAlarmLists() {

        List<AlarmLists> lists = alarmUseCase.getAlarmLists();
        return ApplicationResponse.ok(lists, "성공적으로 알림 목록을 조회했습니다.");
    }

    /**
     * 링크 누를 시 api 호출 필요 ( 조회 여부 판단 )
     */
    @PostMapping("/view")
    public ApplicationResponse<Void> saveAlarmView(Long alarmId) {

        alarmUseCase.saveAlarmView(alarmId);
        return ApplicationResponse.ok(null, "성공적으로 조회 기록을 적재하였습니다.");
    }

    /**
     * 알림 삭제
     */
    @DeleteMapping("/delete")
    public ApplicationResponse<Void> deleteAlarm(Long alarmId) {

        alarmDeleteUseCase.deleteAlarm(alarmId);
        return ApplicationResponse.ok(null, "성공적으로 알림을 삭제했습니다.");
    }

}
