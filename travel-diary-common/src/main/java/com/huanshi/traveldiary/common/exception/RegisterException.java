package com.huanshi.traveldiary.common.exception;

public class RegisterException extends RuntimeException {
    public RegisterException() {
        super("注册失败");
    }
}