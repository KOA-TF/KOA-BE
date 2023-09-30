package com.koa.apimodule.auth.security;

import com.koa.apimodule.auth.security.filter.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.cors().disable();

        http.addFilterBefore(SecurityHandler.getJWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(SecurityHandler.getJWTEntryPoint(), JWTAuthenticationFilter.class);

        http.csrf().disable();

        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/h2-console/**").permitAll();
            request.anyRequest().permitAll(); // 다른 요청은 모두 허용
        });
        http.formLogin().disable();
        http.httpBasic().disable();
        http.logout().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();

        return http.build();
    }

}