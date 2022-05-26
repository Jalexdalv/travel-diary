package com.huanshi.traveldiary.common.exception;

public class UpdateDetailException extends RuntimeException {
    public UpdateDetailException() {
        super("个人信息修改失败");
    }
}