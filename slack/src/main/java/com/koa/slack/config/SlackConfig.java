package com.koa.slack.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SlackConfig {

    @Bean
    public SlackConfigProperties logConfigProperties(
            @Value("${slack.webhook-url}") String serverWebhookUrl
    ) {
        SlackConfigProperties slackConfigProperties = new SlackConfigProperties();
        slackConfigProperties.setServerWebhookUrl(serverWebhookUrl);
        return slackConfigProperties;
    }
}
