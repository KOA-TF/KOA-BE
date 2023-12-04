package com.koa.coremodule.vote.application.dto;

import java.util.ArrayList;

public record VoteRequest(Long noticeId, String title, ArrayList<String> item) {
}
