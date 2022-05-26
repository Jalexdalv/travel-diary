package com.huanshi.traveldiary.api;

import com.huanshi.traveldiary.common.IpParser;
import com.huanshi.traveldiary.common.Response;
import com.huanshi.traveldiary.pojo.dto.LoginByPasswordDto;
import com.huanshi.traveldiary.pojo.dto.LoginBySmsVerifyDto;
import com.huanshi.traveldiary.pojo.dto.RegisterDto;
import com.huanshi.traveldiary.pojo.dto.SendSmsVerifyCodeDto;
import com.huanshi.traveldiary.pojo.dto.UpdateDetailDto;
import com.huanshi.traveldiary.pojo.dto.UpdatePasswordDto;
import com.huanshi.traveldiary.pojo.vo.LoginVo;
import com.huanshi.traveldiary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserAPI {
    @Autowired
    private IpParser ipParser;
    @Autowired
    private UserService userService;

    @PostMapping("/send-login-sms-verify-code")
    @NotNull
    public Response sendLoginSmsVerifyCode(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        userService.sendLoginSmsVerifyCode(sendSmsVerifyCodeDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 发送短信验证码 " + sendSmsVerifyCodeDto.getPhone() + " 成功");
        return Response.success("短信验证码发送成功");
    }

    @PostMapping("/password-login")
    @NotNull
    public Response login(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid LoginByPasswordDto loginByPasswordDto) {
        LoginVo loginVo = userService.login(loginByPasswordDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 登录账号id " + loginVo.getId() + " 成功");
        return Response.success("登录成功", loginVo);
    }

    @PostMapping("/sms-verify-login")
    @NotNull
    public Response login(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid LoginBySmsVerifyDto loginBySmsVerifyDto) {
        LoginVo loginVo = userService.login(loginBySmsVerifyDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 登录账号id " + loginVo.getId() + " 成功");
        return Response.success("登录成功", loginVo);
    }

    @PostMapping("/send-register-sms-verify-code")
    @NotNull
    public Response sendRegisterSmsVerifyCode(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        userService.sendRegisterSmsVerifyCode(sendSmsVerifyCodeDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 发送短信验证码 " + sendSmsVerifyCodeDto.getPhone() + " 成功");
        return Response.success("短信验证码发送成功");
    }

    @PostMapping("/register")
    @NotNull
    public Response register(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid RegisterDto registerDto) {
        userService.register(registerDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 注册账号 " + registerDto.getPhone() + " 成功");
        return Response.success("注册成功");
    }

    @PostMapping("/send-update-password-sms-verify-code")
    @NotNull
    public Response sendUpdatePasswordSmsVerifyCode(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        userService.sendUpdatePasswordSmsVerifyCode(sendSmsVerifyCodeDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 发送短信验证码 " + sendSmsVerifyCodeDto.getPhone() + " 成功");
        return Response.success("短信验证码发送成功");
    }

    @PostMapping("/update-password")
    @NotNull
    public Response updatePassword(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid UpdatePasswordDto updatePasswordDto) {
        userService.updatePassword(updatePasswordDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 修改密码 " + updatePasswordDto.getPhone() + " 成功");
        return Response.success("密码修改成功");
    }

    @PostMapping("/update-detail")
    @NotNull
    public Response updateDetail(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid UpdateDetailDto updateDetailDto) {
        updateDetailDto.setId(Long.parseLong(StringUtils.trimToNull(httpServletRequest.getHeader("id"))));
        userService.updateDetail(updateDetailDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 修改用户id " + updateDetailDto.getId() + " 的个人信息成功");
        return Response.success("个人信息修改成功");
    }
}