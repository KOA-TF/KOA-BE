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
            @Value("https://hooks.slack.com/services/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") String serverWebhookUrl
    ) {
        SlackConfigProperties slackConfigProperties = new SlackConfigProperties();
        slackConfigProperties.setServerWebhookUrl(serverWebhookUrl);
        return slackConfigProperties;
    }
}
