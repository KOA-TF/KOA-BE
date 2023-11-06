package com.koa.coremodule.email.service;

import com.koa.commonmodule.exception.Error;
import com.koa.commonmodule.utils.RedisUtils;
import com.koa.coremodule.email.exception.WrongCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerifyService {

    private final RedisUtils redisUtils;

    public void verifyCode(String email, String code) {
        String savedCode = redisUtils.getData(email);
        if (!code.equals(savedCode)) {
            throw new WrongCodeException(Error.WRONG_CODE);
        }
    }
}
