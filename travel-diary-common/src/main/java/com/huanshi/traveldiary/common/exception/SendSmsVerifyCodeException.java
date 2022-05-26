package com.huanshi.traveldiary.common.exception;

public class SendSmsVerifyCodeException extends RuntimeException {
    public SendSmsVerifyCodeException() {
        super("短信验证码发送失败");
    }
}