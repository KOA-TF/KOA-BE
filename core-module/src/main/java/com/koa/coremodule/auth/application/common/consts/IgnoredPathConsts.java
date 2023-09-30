package com.koa.coremodule.auth.application.common.consts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IgnoredPathConsts {

    @Getter
    private static Map<String, Set<HttpMethod>> ignoredPath = Map.of(
            "/docs/**", Set.of(HttpMethod.GET),
            "/oauth/**", Set.of(HttpMethod.GET),
            "/h2-console/**", Set.of(HttpMethod.GET, HttpMethod.POST),
            "/auth/login/**", Set.of(HttpMethod.GET)
    );

}