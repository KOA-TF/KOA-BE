package com.koa.slack.service;

import com.koa.slack.config.SlackConfigProperties;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.slack.api.model.Attachments.asAttachments;
import static com.slack.api.model.Attachments.attachment;
import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.webhook.WebhookPayloads.payload;

@Slf4j
@Service
public class SlackService {

    private final SlackConfigProperties properties;

    public SlackService(
            @Qualifier("logConfigProperties") SlackConfigProperties properties
    ) {
        this.properties = properties;
    }

    public void sendingExceptionMessage(Exception exception) {
        // Slack 메세지 보내기
        try {
            StackTraceElement[] stackTrace = exception.getStackTrace();
            String className = stackTrace[0].getClassName();
            String methodName = stackTrace[0].getMethodName();
            int lineNumber = stackTrace[0].getLineNumber();

            String title = exception.getClass().getName();
            String errorMessage = exception.getLocalizedMessage();

            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw));

            String stackTraceString = sw.toString();

            String formattedStackTrace = stackTraceString.length() > 2500
                    ? stackTraceString.substring(0, 2500)
                    : stackTraceString;

            log.info("Stack Trace: {}", formattedStackTrace);

            WebhookResponse response = Slack.getInstance().send(properties.getServerWebhookUrl(), payload(p -> p
                    .text("Exception Occurred: " + className)
                    .attachments(asAttachments(
                            attachment(a -> a
                                    .color("#ff0000")
                                    .blocks(asBlocks(
                                            section(section -> section.text(markdownText("*Exception Occurred:* " + className))),
                                            section(section -> section.text(markdownText("*Method:* " + methodName))),
                                            section(section -> section.text(markdownText("*Line:* " + lineNumber))),
                                            section(section -> section.text(markdownText("*Exception:* " + title))),
                                            section(section -> section.text(markdownText("*Exception Message:*"))),
                                            section(section -> section.text(markdownText("```" + errorMessage + "```"))),
                                            section(section -> section.text(markdownText("*Stack Trace:*"))),
                                            section(section -> section.text(markdownText("```" + formattedStackTrace + "```")))
                                    ))
                            )
                    ))
            ));

            log.info("Slack Response: {}", response);

        } catch (IOException e) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.value() + "Can't send Slack Message.");
        }
    }

}
