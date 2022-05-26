package com.huanshi.traveldiary.common.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("密码错误");
    }
}