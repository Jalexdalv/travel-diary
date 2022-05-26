package com.huanshi.traveldiary.mapper.redis;

import org.jetbrains.annotations.Nullable;

public interface UserRedisMapper {
    void updateLoginSmsVerifyCode(long phone, int smsVerifyCode);
    void deleteLoginSmsVerifyCode(long phone);
    @Nullable
    Integer selectLoginSmsVerifyCodeByPhone(long phone);
    void updateRegisterSmsVerifyCode(long phone, int smsVerifyCode);
    void deleteRegisterSmsVerifyCode(long phone);
    @Nullable
    Integer selectRegisterSmsVerifyCodeByPhone(long phone);
    void updateUpdatePasswordSmsVerifyCode(long phone, int smsVerifyCode);
    void deleteUpdatePasswordSmsVerifyCode(long phone);
    @Nullable
    Integer selectUpdatePasswordSmsVerifyCodeByPhone(long phone);
    void updateUserToken(long id, String token);
    @Nullable
    String selectUserToken(long id);
}