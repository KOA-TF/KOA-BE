package com.koa.coremodule.auth.application.common.consts;

import java.util.LinkedHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IgnoredPathConsts {

    @Getter
    private static Map<String, Set<HttpMethod>> ignoredPath = createIgnoredPathMap();

    private static Map<String, Set<HttpMethod>> createIgnoredPathMap() {
        Map<String, Set<HttpMethod>> map = new LinkedHashMap<>();

        map.put("/v1/auth/reissue", Set.of(HttpMethod.GET));
        map.put("/v1/member/register", Set.of(HttpMethod.POST));
        map.put("/v1/member/check/register", Set.of(HttpMethod.POST));
        map.put("/h2-console/**", Set.of(HttpMethod.GET, HttpMethod.POST));
        map.put("/v1/auth/login/**", Set.of(HttpMethod.GET, HttpMethod.POST));
        map.put("/v1/member/email", Set.of(HttpMethod.POST));
        map.put("/v1/member/verify", Set.of(HttpMethod.POST));
        map.put("/v1/member/verify/code", Set.of(HttpMethod.POST));
        map.put("/v1/member/password/unauthenticated", Set.of(HttpMethod.PUT));
        map.put("/api-docs/**", Set.of(HttpMethod.GET, HttpMethod.POST));
        map.put("/error", Set.of(HttpMethod.GET, HttpMethod.POST));
        map.put("/favicon.ico", Set.of(HttpMethod.GET, HttpMethod.POST));
        map.put("/swagger-ui/**", Set.of(HttpMethod.GET, HttpMethod.POST));
        map.put("/swagger-resources/**", Set.of(HttpMethod.GET, HttpMethod.POST));
        map.put("/v3/api-docs/**", Set.of(HttpMethod.GET, HttpMethod.POST));

        return map;
    }
}
