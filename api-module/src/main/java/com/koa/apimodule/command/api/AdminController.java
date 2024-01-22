package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.admin.application.dto.AdminMemberList;
import com.koa.coremodule.admin.application.dto.AdminReportList;
import com.koa.coremodule.admin.application.service.AdminGetUseCase;
import com.koa.coremodule.admin.application.service.AdminSaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class AdminController {

    private final AdminGetUseCase adminGetUseCase;
    private final AdminSaveUseCase adminSaveUseCase;

    /**
     * 어드민 로그인
     */
    @GetMapping(value = "/login")
    public ApplicationResponse<Long> loginAdmin(@RequestParam Integer password) {

        Long adminId = adminGetUseCase.checkPassword(password);
        return ApplicationResponse.ok(adminId, "정상적으로 로그인하였습니다.");
    }

    /**
     * 학회원 관리 리스트
     */
    @GetMapping(value = "/memberlist")
    public ApplicationResponse<List<AdminMemberList>> memberList() {

        List<AdminMemberList> memberLists = adminGetUseCase.getMemberList();
        return ApplicationResponse.ok(memberLists, "학회원 관리 리스트 입니다.");
    }

    /**
     * 학회원 추가
     */

    /**
     * 학회원 수정
     */

    /**
     * 학회원 삭제
     */

    /**
     * 신고 접수 리스트
     */
    @GetMapping(value = "/report")
    public ApplicationResponse<List<AdminReportList>> reportList() {

        List<AdminReportList> reportLists = adminGetUseCase.getReportList();
        return ApplicationResponse.ok(reportLists, "신고 접수 리스트입니다.");
    }

    /**
     * 신고 접수 숨기기
     */
    @PostMapping(value = "/{reportId}/hidereport")
    public ApplicationResponse<Void> hideReport(@PathVariable Long reportId) {

        adminGetUseCase.hideReport(reportId);
        return ApplicationResponse.ok(null, "신고 숨기기에 성공했습니다.");
    }

}
