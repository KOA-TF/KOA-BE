package com.koa.coremodule.member.application.handler.event;

import com.koa.coremodule.member.domain.entity.Link;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LinkUpdateEvent {
    private final Long memberDetailId;
    private final List<Link> links;
}
