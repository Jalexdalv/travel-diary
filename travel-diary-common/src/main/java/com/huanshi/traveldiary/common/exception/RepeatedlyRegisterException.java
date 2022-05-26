package com.huanshi.traveldiary.common.exception;

public class RepeatedlyRegisterException extends RuntimeException {
    public RepeatedlyRegisterException() {
        super("该手机号已被注册");
    }
}