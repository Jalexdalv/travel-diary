package com.huanshi.traveldiary.common.exception;

public class FrequentlySendSmsVerifyCodeException extends RuntimeException {
    public FrequentlySendSmsVerifyCodeException() {
        super("短信验证码发送过于频繁");
    }
}