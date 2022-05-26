package com.huanshi.traveldiary.common.exception;

public class UpdateAvatarException extends RuntimeException {
    public UpdateAvatarException() {
        super("头像修改失败");
    }
}