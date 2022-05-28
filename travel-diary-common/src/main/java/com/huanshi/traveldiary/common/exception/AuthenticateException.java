package com.huanshi.traveldiary.common.exception;

public class AuthenticateException extends RuntimeException {
    public AuthenticateException() {
        super("用户认证失败");
    }
}