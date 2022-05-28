package com.huanshi.traveldiary.api;

import com.huanshi.traveldiary.common.Response;
import com.huanshi.traveldiary.pojo.dto.LoginByPasswordDto;
import com.huanshi.traveldiary.pojo.dto.LoginBySmsVerifyDto;
import com.huanshi.traveldiary.pojo.dto.LogoutDto;
import com.huanshi.traveldiary.pojo.dto.RegisterDto;
import com.huanshi.traveldiary.pojo.dto.SendSmsVerifyCodeDto;
import com.huanshi.traveldiary.pojo.dto.UpdateDetailDto;
import com.huanshi.traveldiary.pojo.dto.UpdatePasswordDto;
import com.huanshi.traveldiary.pojo.vo.LoginVo;
import com.huanshi.traveldiary.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping("/send-login-sms-verify-code")
    @NotNull
    public Response sendLoginSmsVerifyCode(@RequestBody @NotNull @Valid SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        userService.sendLoginSmsVerifyCode(sendSmsVerifyCodeDto);
        return Response.success("短信验证码发送成功");
    }

    @PostMapping("/password-login")
    @NotNull
    public Response login(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid LoginByPasswordDto loginByPasswordDto) {
        loginByPasswordDto.setImei((long) httpServletRequest.getAttribute("imei"));
        LoginVo loginVo = userService.login(loginByPasswordDto);
        return Response.success("登录成功", loginVo);
    }

    @PostMapping("/sms-verify-login")
    @NotNull
    public Response login(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid LoginBySmsVerifyDto loginBySmsVerifyDto) {
        loginBySmsVerifyDto.setImei((long) httpServletRequest.getAttribute("imei"));
        LoginVo loginVo = userService.login(loginBySmsVerifyDto);
        return Response.success("登录成功", loginVo);
    }

    @PostMapping("/send-register-sms-verify-code")
    @NotNull
    public Response sendRegisterSmsVerifyCode(@RequestBody @NotNull @Valid SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        userService.sendRegisterSmsVerifyCode(sendSmsVerifyCodeDto);
        return Response.success("短信验证码发送成功");
    }

    @PostMapping("/register")
    @NotNull
    public Response register(@RequestBody @NotNull @Valid RegisterDto registerDto) {
        userService.register(registerDto);
        return Response.success("注册成功");
    }

    @PostMapping("/send-update-password-sms-verify-code")
    @NotNull
    public Response sendUpdatePasswordSmsVerifyCode(@RequestBody @NotNull @Valid SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        userService.sendUpdatePasswordSmsVerifyCode(sendSmsVerifyCodeDto);
        return Response.success("短信验证码发送成功");
    }

    @PostMapping("/logout")
    @NotNull
    public Response logout(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid LogoutDto logoutDto) {
        logoutDto.setId((long) httpServletRequest.getAttribute("id"));
        logoutDto.setImei((long) httpServletRequest.getAttribute("imei"));
        userService.logout(logoutDto);
        return Response.success("退出成功");
    }

    @PostMapping("/update-password")
    @NotNull
    public Response updatePassword(@RequestBody @NotNull @Valid UpdatePasswordDto updatePasswordDto) {
        userService.updatePassword(updatePasswordDto);
        return Response.success("密码修改成功");
    }

    @PostMapping("/update-detail")
    @NotNull
    public Response updateDetail(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid UpdateDetailDto updateDetailDto) {
        updateDetailDto.setId((long) httpServletRequest.getAttribute("id"));
        userService.updateDetail(updateDetailDto);
        return Response.success("个人信息修改成功");
    }
}