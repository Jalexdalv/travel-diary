package com.huanshi.traveldiary.common.exception;

public class UpdateProfileException extends RuntimeException {
    public UpdateProfileException() {
        super("自我介绍修改失败");
    }
}