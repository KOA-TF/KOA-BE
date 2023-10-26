package com.koa.apimodule.command.api;

import com.koa.coremodule.report.application.service.ReportSaveUseCase;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.report.application.dto.ReportRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/report")
public class ReportController {

    private final ReportSaveUseCase reportSaveUseCase;
    private final MemberUtils memberUtils;

    @PostMapping(value = "")
    public ResponseEntity<Void> reportReply(
            @RequestBody ReportRequest request) {

        Member memberRequest = memberUtils.getAccessMember();

        request.setMemberId(memberRequest.getId());
        Long reportId = reportSaveUseCase.createReport(request);

        return ResponseEntity.created(URI.create("/report/" + reportId)).build();
    }

}
