package com.koa.coremodule.member.application.handler.event;

import com.koa.coremodule.member.domain.entity.Interest;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InterestUpdateEvent {
    private final Long memberDetailId;
    private final List<Interest> interests;
}
