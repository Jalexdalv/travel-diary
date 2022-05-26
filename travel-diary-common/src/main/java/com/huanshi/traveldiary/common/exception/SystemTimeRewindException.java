package com.huanshi.traveldiary.common.exception;

public class SystemTimeRewindException extends RuntimeException {
    public SystemTimeRewindException() {
        super("系统时间发生倒退");
    }
}