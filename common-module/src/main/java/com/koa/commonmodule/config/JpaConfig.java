package com.koa.commonmodule.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.koa")
@EnableJpaAuditing
@EntityScan(basePackages = "com.koa")
public class JpaConfig {
}

