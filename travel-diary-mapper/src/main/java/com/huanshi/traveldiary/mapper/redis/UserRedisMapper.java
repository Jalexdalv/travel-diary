package com.huanshi.traveldiary.mapper.redis;

import com.huanshi.traveldiary.pojo.bo.LoginBo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface UserRedisMapper {
    boolean updateLoginSmsVerifyCode(long phone, int smsVerifyCode);
    boolean updateRegisterSmsVerifyCode(long phone, int smsVerifyCode);
    boolean updateUpdatePasswordSmsVerifyCode(long phone, int smsVerifyCode);
    boolean lockLogin(long id);
    void updateToken(@NotNull LoginBo loginBo);
    boolean deleteLoginSmsVerifyCode(long phone);
    boolean deleteRegisterSmsVerifyCode(long phone);
    boolean deleteUpdatePasswordSmsVerifyCode(long phone);
    boolean unlockLogin(long id);
    boolean deleteToken(long id, long imei);
    @Nullable
    Integer selectLoginSmsVerifyCodeByPhone(long phone);
    @Nullable
    Integer selectRegisterSmsVerifyCodeByPhone(long phone);
    @Nullable
    Integer selectUpdatePasswordSmsVerifyCodeByPhone(long phone);
    @Nullable
    Integer selectLoginDevice(long id);
    @Nullable
    String selectToken(long id, long imei);
}