package com.koa.coremodule.email.service;

import com.koa.commonmodule.exception.Error;
import com.koa.commonmodule.utils.RedisUtils;
import com.koa.coremodule.email.exception.CreateCodeException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private static final int LENGTH = 6;
    private static final String MESSAGE= "인증번호는 %s 입니다.";
    private static final String TITLE = "KOA 이메일 인증";
    private final JavaMailSender emailSender;
    private final RedisUtils redisUtils;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public void sendEmail(String email) {
        String code = createCode();
        SimpleMailMessage emailForm = createEmailForm(email, TITLE, String.format(MESSAGE, code));
        emailSender.send(emailForm);
        redisUtils.setDataExpire(email, code, authCodeExpirationMillis);
    }

    private String createCode() {
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CreateCodeException(Error.CREATE_CODE_FAIL);
        }
    }

    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}
