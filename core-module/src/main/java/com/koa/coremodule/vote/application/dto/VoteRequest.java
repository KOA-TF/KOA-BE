package com.koa.coremodule.vote.application.dto;

import java.util.ArrayList;

public record VoteRequest(String title, ArrayList<String> item) {
}
