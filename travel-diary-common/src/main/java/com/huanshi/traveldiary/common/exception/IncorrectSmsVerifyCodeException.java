package com.huanshi.traveldiary.common.exception;

public class IncorrectSmsVerifyCodeException extends RuntimeException {
    public IncorrectSmsVerifyCodeException() {
        super("短信验证码错误");
    }
}