package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.fcm.application.dto.AlarmLists;
import com.koa.coremodule.fcm.application.service.AlarmUseCase;
import com.koa.coremodule.fcm.domain.entity.Alarm;
import com.koa.coremodule.fcm.domain.entity.AlarmType;
import com.koa.coremodule.fcm.domain.repository.AlarmRepository;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.notice.application.dto.fcm.RegisterTokenRequest;
import com.koa.coremodule.notice.application.dto.fcm.SendNotificationRequest;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.NoticeTeam;
import com.koa.coremodule.notice.domain.repository.CurriculumRepository;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import com.koa.coremodule.notice.domain.repository.NoticeTeamRepository;
import jakarta.annotation.PostConstruct;
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

    private final MemberRepository memberRepository;
    private final NoticeTeamRepository noticeTeamRepository;
    private final CurriculumRepository curriculumRepository;
    private final NoticeRepository noticeRepository;
    private final AlarmRepository alarmRepository;

    @PostConstruct
    public void test() {
        final Member member = Member.builder()
                .authority(Authority.MEMBER)
                .email("austinan123@gmail.com")
                .password("001215")
                .name("안정후")
                .build();
        memberRepository.save(member);
        final Curriculum curriculum = Curriculum.builder()
                .curriculumName("기업프로젝트")
                .build();
        curriculumRepository.save(curriculum);
        final NoticeTeam noticeTeam = NoticeTeam.builder()
                .teamName("경영총괄팀")
                .build();
        noticeTeamRepository.save(noticeTeam);
        final Notice notice = Notice.builder()
                .member(member)
                .title("sampletitle")
                .content("samplecontent")
                .curriculum(curriculum)
                .build();
        noticeRepository.save(notice);
        final Notice notice2 = Notice.builder()
                .member(member)
                .title("sampletitle22")
                .content("samplecontent22")
                .curriculum(curriculum)
                .build();
        noticeRepository.save(notice2);
        final Alarm alarm = Alarm.builder().type(AlarmType.NOTICE).title("공지알림").content("notice content").build();
        alarmRepository.save(alarm);
        final Alarm alar2 = Alarm.builder().type(AlarmType.COMMENT).title("댓글알림").content("comment content").build();
        alarmRepository.save(alar2);
    }


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
    public ApplicationResponse<Void> sendNoticeNotification(@RequestBody SendNotificationRequest request) {

        alarmUseCase.sendNoticeNotification(request);
        return ApplicationResponse.ok(null, "성공적으로 공지 알림을 보냈습니다.");
    }

    /**
     * 메세지 전달 ( 댓글 알림 )
     */
    @PostMapping("/send/comment")
    public ApplicationResponse<Void> sendCommentNotification(@RequestBody SendNotificationRequest request) {

        alarmUseCase.sendCommentNotification(request);
        return ApplicationResponse.ok(null, "성공적으로 댓글 알림을 보냈습니다.");
    }

    /**
     * 메세지 전달 ( 대댓글 알림 )
     */
    @PostMapping("/send/recomment")
    public ApplicationResponse<Void> sendReCommentNotification(@RequestBody SendNotificationRequest request) {

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

        alarmUseCase.deleteAlarm(alarmId);
        return ApplicationResponse.ok(null, "성공적으로 알림을 삭제했습니다.");
    }

}
