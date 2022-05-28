package com.huanshi.traveldiary.common.exception;

public class ImeiException extends RuntimeException {
    public ImeiException() {
        super("设备识别码格式不正确");
    }
}