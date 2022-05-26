package com.huanshi.traveldiary.common.exception;

public class UnregisteredException extends RuntimeException {
    public UnregisteredException() {
        super("该手机号码未注册");
    }
}