package com.huanshi.traveldiary.common.exception;

public class InitializeIdWorkerException extends RuntimeException {
    public InitializeIdWorkerException() {
        super("ID生成器初始化失败");
    }
}