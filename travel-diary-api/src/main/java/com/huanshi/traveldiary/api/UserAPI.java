package com.huanshi.traveldiary.api;

import com.huanshi.traveldiary.common.IpParser;
import com.huanshi.traveldiary.common.Response;
import com.huanshi.traveldiary.pojo.dto.LoginByPasswordDto;
import com.huanshi.traveldiary.pojo.dto.LoginBySmsVerifyDto;
import com.huanshi.traveldiary.pojo.dto.RegisterDto;
import com.huanshi.traveldiary.pojo.dto.SendSmsVerifyCodeDto;
import com.huanshi.traveldiary.pojo.dto.UpdateAvatarDto;
import com.huanshi.traveldiary.pojo.dto.UpdateNicknameDto;
import com.huanshi.traveldiary.pojo.dto.UpdatePasswordDto;
import com.huanshi.traveldiary.pojo.dto.UpdateProfileDto;
import com.huanshi.traveldiary.pojo.dto.UpdateSexDto;
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

    @PostMapping("/update-nickname")
    @NotNull
    public Response updateNickname(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid UpdateNicknameDto updateNicknameDto) {
        updateNicknameDto.setId(Long.parseLong(StringUtils.trimToNull(httpServletRequest.getHeader("id"))));
        userService.updateNickname(updateNicknameDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 修改用户id " + updateNicknameDto.getId() + " 的昵称成功");
        return Response.success("昵称修改成功");
    }

    @PostMapping("/update-sex")
    @NotNull
    public Response updateSex(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid UpdateSexDto updateSexDto) {
        updateSexDto.setId(Long.parseLong(StringUtils.trimToNull(httpServletRequest.getHeader("id"))));
        userService.updateSex(updateSexDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 修改用户id " + updateSexDto.getId() + " 的性别成功");
        return Response.success("性别修改成功");
    }

    @PostMapping("/update-avatar")
    @NotNull
    public Response updateAvatar(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid UpdateAvatarDto updateAvatarDto) {
        updateAvatarDto.setId(Long.parseLong(StringUtils.trimToNull(httpServletRequest.getHeader("id"))));
        userService.updateAvatar(updateAvatarDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 修改用户id " + updateAvatarDto.getId() + " 的头像成功");
        return Response.success("头像修改成功");
    }

    @PostMapping("/update-profile")
    @NotNull
    public Response updateProfile(@NotNull HttpServletRequest httpServletRequest, @RequestBody @NotNull @Valid UpdateProfileDto updateProfileDto) {
        updateProfileDto.setId(Long.parseLong(StringUtils.trimToNull(httpServletRequest.getHeader("id"))));
        userService.updateProfile(updateProfileDto);
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 修改用户id " + updateProfileDto.getId() + " 的自我介绍成功");
        return Response.success("自我介绍修改成功");
    }
}