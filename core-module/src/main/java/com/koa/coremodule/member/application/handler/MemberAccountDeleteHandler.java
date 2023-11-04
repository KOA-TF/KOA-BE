package com.koa.coremodule.member.application.handler;

import com.koa.coremodule.member.application.handler.event.MemberAccountDeleteEvent;
import com.koa.coremodule.member.domain.service.MemberDetailDeleteService;
import com.koa.coremodule.notice.domain.service.NoticeDeleteService;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MemberAccountDeleteHandler {
    private final MemberDetailDeleteService memberDetailDeleteService;
    private final NoticeDeleteService noticeDeleteService;
    private final NoticeQueryService noticeQueryService;

    @EventListener
    public void deleteMemberAccount(MemberAccountDeleteEvent memberAccountDeleteEvent){
        final List<Long> noticeIds = noticeQueryService.findNoticeIdsByMemberId(memberAccountDeleteEvent.getMemberId());
        memberDetailDeleteService.deleteMemberDetailByMemberId(memberAccountDeleteEvent.getMemberId());
        noticeDeleteService.deleteNoticeByNoticeIds(noticeIds);
    }


}
