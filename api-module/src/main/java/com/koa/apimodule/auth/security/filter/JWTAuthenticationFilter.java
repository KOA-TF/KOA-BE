package com.koa.apimodule.auth.security.filter;

import com.koa.apimodule.auth.security.JWTAuthenticationToken;
import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.common.consts.IgnoredPathConsts;
import com.koa.coremodule.auth.application.service.JWTExtractEmailUseCase;
import com.koa.coremodule.auth.application.service.JWTExtractTokenUseCase;
import com.koa.coremodule.auth.application.service.JWTVerifyUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTVerifyUseCase jwtVerifyUseCase;
    private final JWTExtractEmailUseCase jwtExtractEmailUseCase;
    private final JWTExtractTokenUseCase jwtExtractTokenUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!isIgnoredPath(request)){
            final String tokenHeaderValue = getTokenFromHeader(request);
            final String accessToken = jwtExtractTokenUseCase.extractToken(tokenHeaderValue);
            jwtVerifyUseCase.validateToken(accessToken);
            final String email = jwtExtractEmailUseCase.extractEmail(accessToken);
            initAuthentication(new JWTAuthenticationToken(email));
        }
        filterChain.doFilter(request, response);
    }


    private String getTokenFromHeader(HttpServletRequest request){
        String header = request.getHeader(AuthConsts.AUTHORIZATION);
        return StringUtils.hasText(header)? header : AuthConsts.EMPTY_HEADER;
    }

    private Boolean isIgnoredPath(HttpServletRequest request){
        final Set<String> ignoredPathURI = IgnoredPathConsts.getIgnoredPath().keySet();
        return ignoredPathURI.stream()
                .anyMatch(ignoredPath -> isMatchingPath(request, ignoredPath)&&isMatchingMethod(request, ignoredPath));
    }

    private Boolean isMatchingPath(HttpServletRequest request, String ignoredPathURI){
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher.match(ignoredPathURI, request.getRequestURI());
    }

    private Boolean isMatchingMethod(HttpServletRequest request, String ignoredPathURI) {
        Set<HttpMethod> allowedMethods = IgnoredPathConsts.getIgnoredPath().get(ignoredPathURI);
        if (allowedMethods != null) {
            HttpMethod requestMethod = HttpMethod.valueOf(request.getMethod());
            return allowedMethods.contains(requestMethod);
        }
        return false;
    }

    private void initAuthentication(final Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
