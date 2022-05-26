package com.huanshi.traveldiary.mapper.redis.impl;

import com.huanshi.traveldiary.mapper.redis.UserRedisMapper;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserRedisMapperImpl implements UserRedisMapper {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void updateLoginSmsVerifyCode(long phone, int smsVerifyCode) {
        redisTemplate.opsForValue().setIfAbsent("login-sms-verify-code:" + phone, smsVerifyCode, 60, TimeUnit.SECONDS);
    }

    @Override
    public void deleteLoginSmsVerifyCode(long phone) {
        redisTemplate.delete("login-sms-verify-code:" + phone);
    }

    @Override
    @Nullable
    public Integer selectLoginSmsVerifyCodeByPhone(long phone) {
        return (Integer) redisTemplate.opsForValue().get("login-sms-verify-code:" + phone);
    }

    @Override
    public void updateRegisterSmsVerifyCode(long phone, int smsVerifyCode) {
        redisTemplate.opsForValue().setIfAbsent("register-sms-verify-code:" + phone, smsVerifyCode, 60, TimeUnit.SECONDS);
    }

    @Override
    public void deleteRegisterSmsVerifyCode(long phone) {
        redisTemplate.delete("register-sms-verify-code:" + phone);
    }

    @Override
    @Nullable
    public Integer selectRegisterSmsVerifyCodeByPhone(long phone) {
        return (Integer) redisTemplate.opsForValue().get("register-sms-verify-code:" + phone);
    }

    @Override
    public void updateUpdatePasswordSmsVerifyCode(long phone, int smsVerifyCode) {
        redisTemplate.opsForValue().setIfAbsent("update-password-sms-verify-code:" + phone, smsVerifyCode, 60, TimeUnit.SECONDS);
    }

    @Override
    public void deleteUpdatePasswordSmsVerifyCode(long phone) {
        redisTemplate.delete("update-password-sms-verify-code:" + phone);
    }

    @Override
    @Nullable
    public Integer selectUpdatePasswordSmsVerifyCodeByPhone(long phone) {
        return (Integer) redisTemplate.opsForValue().get("update-password-sms-verify-code:" + phone);
    }

    @Override
    public void updateUserToken(long id, String token) {
        redisTemplate.opsForValue().set(Long.toString(id), token, 24, TimeUnit.HOURS);
    }

    @Override
    @Nullable
    public String selectUserToken(long id) {
        return (String) redisTemplate.opsForValue().get(Long.toString(id));
    }
}