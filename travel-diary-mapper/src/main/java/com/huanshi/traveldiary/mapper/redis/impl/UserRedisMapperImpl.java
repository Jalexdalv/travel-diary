package com.huanshi.traveldiary.mapper.redis.impl;

import com.huanshi.traveldiary.mapper.redis.UserRedisMapper;
import com.huanshi.traveldiary.pojo.bo.LoginBo;
import org.jetbrains.annotations.NotNull;
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
    public boolean updateLoginSmsVerifyCode(long phone, int smsVerifyCode) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent("login-sms-verify-code:" + phone, smsVerifyCode, 60, TimeUnit.SECONDS));
    }

    @Override
    public boolean updateRegisterSmsVerifyCode(long phone, int smsVerifyCode) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent("register-sms-verify-code:" + phone, smsVerifyCode, 60, TimeUnit.SECONDS));
    }

    @Override
    public boolean updateUpdatePasswordSmsVerifyCode(long phone, int smsVerifyCode) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent("update-password-sms-verify-code:" + phone, smsVerifyCode, 60, TimeUnit.SECONDS));
    }

    @Override
    public boolean lockLogin(long id) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent("login-lock:" + id, "lock"));
    }

    @Override
    public void updateToken(@NotNull LoginBo loginBo) {
        redisTemplate.opsForValue().set(loginBo.getId() + ":" + loginBo.getImei(), loginBo.getToken(), 7, TimeUnit.DAYS);
        redisTemplate.opsForValue().increment("login-device:" + loginBo.getId());
    }

    @Override
    public boolean deleteLoginSmsVerifyCode(long phone) {
        return Boolean.TRUE.equals(redisTemplate.delete("login-sms-verify-code:" + phone));
    }

    @Override
    public boolean deleteRegisterSmsVerifyCode(long phone) {
        return Boolean.TRUE.equals(redisTemplate.delete("register-sms-verify-code:" + phone));
    }

    @Override
    public boolean deleteUpdatePasswordSmsVerifyCode(long phone) {
        return Boolean.TRUE.equals(redisTemplate.delete("update-password-sms-verify-code:" + phone));
    }

    @Override
    public boolean unlockLogin(long id) {
        return Boolean.TRUE.equals(redisTemplate.delete("login-lock:" + id));
    }

    @Override
    public boolean deleteToken(long id, long imei) {
        redisTemplate.opsForValue().decrement("login-device:" + id);
        return Boolean.TRUE.equals(redisTemplate.delete(id + ":" + imei));
    }

    @Override
    @Nullable
    public Integer selectLoginSmsVerifyCodeByPhone(long phone) {
        return (Integer) redisTemplate.opsForValue().get("login-sms-verify-code:" + phone);
    }

    @Override
    @Nullable
    public Integer selectRegisterSmsVerifyCodeByPhone(long phone) {
        return (Integer) redisTemplate.opsForValue().get("register-sms-verify-code:" + phone);
    }

    @Override
    @Nullable
    public Integer selectUpdatePasswordSmsVerifyCodeByPhone(long phone) {
        return (Integer) redisTemplate.opsForValue().get("update-password-sms-verify-code:" + phone);
    }

    @Override
    @Nullable
    public Integer selectLoginDevice(long id) {
        return (Integer) redisTemplate.opsForValue().get("login-device:" + id);
    }

    @Override
    @Nullable
    public String selectToken(long id, long imei) {
        return (String) redisTemplate.opsForValue().get(id + ":" + imei);
    }
}