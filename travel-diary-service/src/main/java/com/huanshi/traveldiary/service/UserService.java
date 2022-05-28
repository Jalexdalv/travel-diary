package com.huanshi.traveldiary.service;

import com.huanshi.traveldiary.pojo.dto.LoginByPasswordDto;
import com.huanshi.traveldiary.pojo.dto.LoginBySmsVerifyDto;
import com.huanshi.traveldiary.pojo.dto.LogoutDto;
import com.huanshi.traveldiary.pojo.dto.RegisterDto;
import com.huanshi.traveldiary.pojo.dto.SendSmsVerifyCodeDto;
import com.huanshi.traveldiary.pojo.dto.UpdateDetailDto;
import com.huanshi.traveldiary.pojo.dto.UpdatePasswordDto;
import com.huanshi.traveldiary.pojo.vo.LoginVo;
import org.jetbrains.annotations.NotNull;

public interface UserService {
    void sendLoginSmsVerifyCode(@NotNull SendSmsVerifyCodeDto sendSmsVerifyCodeDto);
    @NotNull
    LoginVo login(@NotNull LoginByPasswordDto loginByPasswordDto);
    @NotNull
    LoginVo login(@NotNull LoginBySmsVerifyDto loginBySmsVerifyDto);
    void sendRegisterSmsVerifyCode(@NotNull SendSmsVerifyCodeDto sendSmsVerifyCodeDto);
    void register(@NotNull RegisterDto registerDto);
    void logout(@NotNull LogoutDto logoutDto);
    void sendUpdatePasswordSmsVerifyCode(@NotNull SendSmsVerifyCodeDto sendSmsVerifyCodeDto);
    void updatePassword(@NotNull UpdatePasswordDto updatePasswordDto);
    void updateDetail(@NotNull UpdateDetailDto updateDetailDto);
}