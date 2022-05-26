package com.huanshi.traveldiary.common.exception;

public class LoginException extends RuntimeException {
    public LoginException() {
        super("登录失败");
    }
}