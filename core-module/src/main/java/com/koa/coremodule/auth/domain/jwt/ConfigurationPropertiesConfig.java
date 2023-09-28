package com.koa.coremodule.auth.domain.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JWTProperties.class)
public class ConfigurationPropertiesConfig {
}
//JWTProperties 클래스는 @ConfigurationProperties 어노테이션을 사용하여 외부 속성 파일(application.yml 또는 application.properties)에서 값을 주입받아야
//하기 때문에 Spring의 IoC 컨테이너에 등록되어야함.