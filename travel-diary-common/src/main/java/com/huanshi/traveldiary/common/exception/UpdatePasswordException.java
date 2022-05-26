package com.huanshi.traveldiary.common.exception;

public class UpdatePasswordException extends RuntimeException {
    public UpdatePasswordException() {
        super("密码修改失败");
    }
}