package com.huanshi.traveldiary.exception;

import com.huanshi.traveldiary.common.Response;
import com.huanshi.traveldiary.common.exception.AuthenticateException;
import com.huanshi.traveldiary.common.exception.FrequentlySendSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.ImeiException;
import com.huanshi.traveldiary.common.exception.IncorrectPasswordException;
import com.huanshi.traveldiary.common.exception.IncorrectSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.InitializeIdWorkerException;
import com.huanshi.traveldiary.common.exception.LoginException;
import com.huanshi.traveldiary.common.exception.RegisterException;
import com.huanshi.traveldiary.common.exception.RepeatedlyRegisterException;
import com.huanshi.traveldiary.common.exception.SendSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.SystemTimeRewindException;
import com.huanshi.traveldiary.common.exception.UnregisteredException;
import com.huanshi.traveldiary.common.exception.UpdateDetailException;
import com.huanshi.traveldiary.common.exception.UpdatePasswordException;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BindException.class)
    @NotNull
    public Response bindExceptionHandler(@NotNull BindException bindException) {
        return Response.error(bindException.getMessage());
    }

    @ExceptionHandler(value = FrequentlySendSmsVerifyCodeException.class)
    @NotNull
    public Response frequentlySendSmsVerifyCodeExceptionHandler(@NotNull FrequentlySendSmsVerifyCodeException frequentlySendSmsVerifyCodeException) {
        return Response.error(frequentlySendSmsVerifyCodeException.getMessage());
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    @NotNull
    public Response incorrectPasswordExceptionHandler(@NotNull IncorrectPasswordException incorrectPasswordException) {
        return Response.error(incorrectPasswordException.getMessage());
    }

    @ExceptionHandler(value = IncorrectSmsVerifyCodeException.class)
    @NotNull
    public Response incorrectSmsVerifyCodeExceptionHandler(@NotNull IncorrectSmsVerifyCodeException incorrectSmsVerifyCodeException) {
        return Response.error(incorrectSmsVerifyCodeException.getMessage());
    }

    @ExceptionHandler(value = InitializeIdWorkerException.class)
    @NotNull
    public Response initializeIdWorkerExceptionHandler(@NotNull InitializeIdWorkerException initializeIdWorkerException) {
        return Response.error(initializeIdWorkerException.getMessage());
    }

    @ExceptionHandler(value = LoginException.class)
    @NotNull
    public Response loginExceptionHandler(@NotNull LoginException loginException) {
        return Response.error(loginException.getMessage());
    }

    @ExceptionHandler(value = RegisterException.class)
    @NotNull
    public Response registerExceptionHandler(@NotNull RegisterException registerException) {
        return Response.error(registerException.getMessage());
    }

    @ExceptionHandler(value = RepeatedlyRegisterException.class)
    @NotNull
    public Response repeatedlyRegisterExceptionHandler(@NotNull RepeatedlyRegisterException repeatedlyRegisterException) {
        return Response.error(repeatedlyRegisterException.getMessage());
    }

    @ExceptionHandler(value = SendSmsVerifyCodeException.class)
    @NotNull
    public Response sendSmsVerifyCodeExceptionHandler(@NotNull SendSmsVerifyCodeException sendSmsVerifyCodeException) {
        return Response.error(sendSmsVerifyCodeException.getMessage());
    }

    @ExceptionHandler(value = SystemTimeRewindException.class)
    @NotNull
    public Response systemTimeRewindExceptionHandler(@NotNull SystemTimeRewindException systemTimeRewindException) {
        return Response.error(systemTimeRewindException.getMessage());
    }

    @ExceptionHandler(value = UnregisteredException.class)
    @NotNull
    public Response unregisteredExceptionHandler(@NotNull UnregisteredException unregisteredException) {
        return Response.error(unregisteredException.getMessage());
    }

    @ExceptionHandler(value = UpdateDetailException.class)
    @NotNull
    public Response updateDetailExceptionHandler(@NotNull UpdateDetailException updateDetailException) {
        return Response.error(updateDetailException.getMessage());
    }

    @ExceptionHandler(value = UpdatePasswordException.class)
    @NotNull
    public Response updatePasswordExceptionHandler(@NotNull UpdatePasswordException updatePasswordException) {
        return Response.error(updatePasswordException.getMessage());
    }

    @ExceptionHandler(value = ImeiException.class)
    @NotNull
    public Response imeiExceptionHandler(@NotNull ImeiException imeiException) {
        return Response.error(imeiException.getMessage());
    }

    @ExceptionHandler(value = AuthenticateException.class)
    @NotNull
    public Response authenticateExceptionHandler(@NotNull AuthenticateException authenticateException) {
        return Response.error(authenticateException.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    @NotNull
    public Response throwableHandler(@NotNull Throwable throwable) {
        return Response.error("服务器发生错误：" + throwable.getMessage());
    }
}