package com.koa.coremodule.email.service;

import com.koa.commonmodule.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerifyService {

    private final RedisUtils redisUtils;

    public boolean verifyCode(String email, String code) {
        String savedCode = redisUtils.getData(email);
        return code.equals(savedCode);
    }
}
