package com.koa.coremodule.email.service;

import com.koa.commonmodule.utils.RedisUtils;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private static final int CODE_LENGTH = 6;
    private static final String MESSAGE_TEMPLATE = "인증번호는 %s 입니다.";
    private static final String EMAIL_TITLE = "KOA 이메일 인증";

    private final JavaMailSender emailSender;
    private final RedisUtils redisUtils;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public void sendEmail(String email) {
        String code = createCode();
        String messageText = String.format(MESSAGE_TEMPLATE, code);
        SimpleMailMessage emailForm = createEmailForm(email, EMAIL_TITLE, messageText);
        emailSender.send(emailForm);
        redisUtils.setDataExpire(email, code, authCodeExpirationMillis);
    }

    private String createCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            codeBuilder.append(random.nextInt(10));
        }
        return codeBuilder.toString();
    }

    private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}
