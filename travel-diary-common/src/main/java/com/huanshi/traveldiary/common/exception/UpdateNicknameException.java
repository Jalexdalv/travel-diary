package com.huanshi.traveldiary.common.exception;

public class UpdateNicknameException extends RuntimeException {
    public UpdateNicknameException() {
        super("昵称修改失败");
    }
}