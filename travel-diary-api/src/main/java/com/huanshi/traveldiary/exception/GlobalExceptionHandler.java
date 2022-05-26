package com.huanshi.traveldiary.exception;

import com.huanshi.traveldiary.common.IpParser;
import com.huanshi.traveldiary.common.Response;
import com.huanshi.traveldiary.common.exception.FrequentlySendSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.IncorrectPasswordException;
import com.huanshi.traveldiary.common.exception.IncorrectSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.InitializeIdWorkerException;
import com.huanshi.traveldiary.common.exception.LoginException;
import com.huanshi.traveldiary.common.exception.RegisterException;
import com.huanshi.traveldiary.common.exception.RepeatedlyRegisterException;
import com.huanshi.traveldiary.common.exception.SendSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.SystemTimeRewindException;
import com.huanshi.traveldiary.common.exception.UnregisteredException;
import com.huanshi.traveldiary.common.exception.UpdateAvatarException;
import com.huanshi.traveldiary.common.exception.UpdateNicknameException;
import com.huanshi.traveldiary.common.exception.UpdatePasswordException;
import com.huanshi.traveldiary.common.exception.UpdateProfileException;
import com.huanshi.traveldiary.common.exception.UpdateSexException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private IpParser ipParser;

    @ExceptionHandler(value = BindException.class)
    @NotNull
    public Response bindExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull BindException bindException) {
        log.error("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 参数校验出现异常", bindException);
        return Response.error(bindException.getMessage());
    }

    @ExceptionHandler(value = FrequentlySendSmsVerifyCodeException.class)
    @NotNull
    public Response frequentlySendSmsVerifyCodeExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull FrequentlySendSmsVerifyCodeException frequentlySendSmsVerifyCodeException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", frequentlySendSmsVerifyCodeException);
        return Response.error(frequentlySendSmsVerifyCodeException.getMessage());
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    @NotNull
    public Response incorrectPasswordExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull IncorrectPasswordException incorrectPasswordException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", incorrectPasswordException);
        return Response.error(incorrectPasswordException.getMessage());
    }

    @ExceptionHandler(value = IncorrectSmsVerifyCodeException.class)
    @NotNull
    public Response incorrectSmsVerifyCodeExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull IncorrectSmsVerifyCodeException incorrectSmsVerifyCodeException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", incorrectSmsVerifyCodeException);
        return Response.error(incorrectSmsVerifyCodeException.getMessage());
    }

    @ExceptionHandler(value = InitializeIdWorkerException.class)
    @NotNull
    public Response initializeIdWorkerExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull InitializeIdWorkerException initializeIdWorkerException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", initializeIdWorkerException);
        return Response.error(initializeIdWorkerException.getMessage());
    }

    @ExceptionHandler(value = LoginException.class)
    @NotNull
    public Response loginExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull LoginException loginException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", loginException);
        return Response.error(loginException.getMessage());
    }

    @ExceptionHandler(value = RegisterException.class)
    @NotNull
    public Response registerExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull RegisterException registerException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", registerException);
        return Response.error(registerException.getMessage());
    }

    @ExceptionHandler(value = RepeatedlyRegisterException.class)
    @NotNull
    public Response repeatedlyRegisterExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull RepeatedlyRegisterException repeatedlyRegisterException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", repeatedlyRegisterException);
        return Response.error(repeatedlyRegisterException.getMessage());
    }

    @ExceptionHandler(value = SendSmsVerifyCodeException.class)
    @NotNull
    public Response sendSmsVerifyCodeExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull SendSmsVerifyCodeException sendSmsVerifyCodeException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", sendSmsVerifyCodeException);
        return Response.error(sendSmsVerifyCodeException.getMessage());
    }

    @ExceptionHandler(value = SystemTimeRewindException.class)
    @NotNull
    public Response systemTimeRewindExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull SystemTimeRewindException systemTimeRewindException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", systemTimeRewindException);
        return Response.error(systemTimeRewindException.getMessage());
    }

    @ExceptionHandler(value = UnregisteredException.class)
    @NotNull
    public Response unregisteredExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull UnregisteredException unregisteredException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", unregisteredException);
        return Response.error(unregisteredException.getMessage());
    }

    @ExceptionHandler(value = UpdateAvatarException.class)
    @NotNull
    public Response updateAvatarExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull UpdateAvatarException updateAvatarException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", updateAvatarException);
        return Response.error(updateAvatarException.getMessage());
    }

    @ExceptionHandler(value = UpdateNicknameException.class)
    @NotNull
    public Response updateNicknameExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull UpdateNicknameException updateNicknameException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", updateNicknameException);
        return Response.error(updateNicknameException.getMessage());
    }

    @ExceptionHandler(value = UpdatePasswordException.class)
    @NotNull
    public Response updatePasswordExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull UpdatePasswordException updatePasswordException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", updatePasswordException);
        return Response.error(updatePasswordException.getMessage());
    }

    @ExceptionHandler(value = UpdateProfileException.class)
    @NotNull
    public Response updateProfileExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull UpdateProfileException updateProfileException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", updateProfileException);
        return Response.error(updateProfileException.getMessage());
    }

    @ExceptionHandler(value = UpdateSexException.class)
    @NotNull
    public Response updateSexExceptionHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull UpdateSexException updateSexException) {
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", updateSexException);
        return Response.error(updateSexException.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    @NotNull
    public Response throwableHandler(@NotNull HttpServletRequest httpServletRequest, @NotNull Throwable throwable) {
        log.error("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 "  + httpServletRequest.getHeader("id") + " 的请求 " + httpServletRequest.getRequestURI() + " 出现异常", throwable);
        return Response.error("服务器发生错误：" + throwable.getMessage());
    }
}