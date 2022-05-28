package com.huanshi.traveldiary.service.impl;

import com.huanshi.traveldiary.common.IdWorker;
import com.huanshi.traveldiary.common.Md5Cipher;
import com.huanshi.traveldiary.common.exception.FrequentlySendSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.IncorrectPasswordException;
import com.huanshi.traveldiary.common.exception.IncorrectSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.LoginException;
import com.huanshi.traveldiary.common.exception.RegisterException;
import com.huanshi.traveldiary.common.exception.RepeatedlyRegisterException;
import com.huanshi.traveldiary.common.exception.SendSmsVerifyCodeException;
import com.huanshi.traveldiary.common.exception.UnregisteredException;
import com.huanshi.traveldiary.common.exception.UpdateDetailException;
import com.huanshi.traveldiary.common.exception.UpdatePasswordException;
import com.huanshi.traveldiary.mapper.mysql.UserMySQLMapper;
import com.huanshi.traveldiary.mapper.redis.UserRedisMapper;
import com.huanshi.traveldiary.pojo.bo.LoginBo;
import com.huanshi.traveldiary.pojo.bo.UpdateDetailBo;
import com.huanshi.traveldiary.pojo.bo.UpdatePasswordBo;
import com.huanshi.traveldiary.pojo.dto.LoginByPasswordDto;
import com.huanshi.traveldiary.pojo.dto.LoginBySmsVerifyDto;
import com.huanshi.traveldiary.pojo.dto.LogoutDto;
import com.huanshi.traveldiary.pojo.dto.RegisterDto;
import com.huanshi.traveldiary.pojo.dto.SendSmsVerifyCodeDto;
import com.huanshi.traveldiary.pojo.dto.UpdateDetailDto;
import com.huanshi.traveldiary.pojo.dto.UpdatePasswordDto;
import com.huanshi.traveldiary.pojo.po.User;
import com.huanshi.traveldiary.pojo.vo.LoginVo;
import com.huanshi.traveldiary.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private Md5Cipher md5Cipher;
    @Autowired
    private UserMySQLMapper userMySQLMapper;
    @Autowired
    private UserRedisMapper userRedisMapper;

    @Override
    public void sendLoginSmsVerifyCode(@NotNull SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        if (userRedisMapper.selectLoginSmsVerifyCodeByPhone(sendSmsVerifyCodeDto.getPhone()) == null) {
            int smsVerifyCode = (int) ((Math.random() * 9 + 1) * 10000);
            if (userRedisMapper.updateLoginSmsVerifyCode(sendSmsVerifyCodeDto.getPhone(), smsVerifyCode)) {
                try {
                    System.out.println("发送");
                    return;
                } catch (Throwable throwable) {
                    userRedisMapper.deleteLoginSmsVerifyCode(sendSmsVerifyCodeDto.getPhone());
                    throw new SendSmsVerifyCodeException();
                }
            }
        }
        throw new FrequentlySendSmsVerifyCodeException();
    }

    @Override
    @NotNull
    public LoginVo login(@NotNull LoginByPasswordDto loginByPasswordDto) {
        User user = userMySQLMapper.selectByPhone(loginByPasswordDto.getPhone());
        if (user != null) {
            if (md5Cipher.md5Hash32(loginByPasswordDto.getPassword()).equals(user.getPassword())) {
                if (userRedisMapper.lockLogin(user.getId())) {
                    Integer loginDevice = userRedisMapper.selectLoginDevice(user.getId());
                    if (loginDevice != null && loginDevice < 5) {
                        String token = UUID.randomUUID().toString();
                        LoginBo loginBo = new LoginBo(user.getId(), loginByPasswordDto.getImei(), token);
                        userRedisMapper.updateToken(loginBo);
                        userRedisMapper.unlockLogin(loginBo.getId());
                        return new LoginVo(user.getId(), token);
                    }
                }
                throw new LoginException();
            }
            throw new IncorrectPasswordException();
        }
        throw new UnregisteredException();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    @NotNull
    public LoginVo login(@NotNull LoginBySmsVerifyDto loginBySmsVerifyDto) {
        if (loginBySmsVerifyDto.getSmsVerifyCode().equals(userRedisMapper.selectLoginSmsVerifyCodeByPhone(loginBySmsVerifyDto.getPhone()))) {
            User user = userMySQLMapper.selectByPhone(loginBySmsVerifyDto.getPhone());
            if (user == null) {
                long id = idWorker.nextId();
                user = new User();
                user.setId(id);
                user.setPhone(loginBySmsVerifyDto.getPhone());
                user.setNickname(md5Cipher.md5Hash16(Long.toString(id)) + (int) ((Math.random() * 9 + 1) * 1000));
                if (userMySQLMapper.insert(user) != 1) {
                    throw new LoginException();
                }
            }
            if (userRedisMapper.lockLogin(user.getId())) {
                Integer loginDevice = userRedisMapper.selectLoginDevice(user.getId());
                if (loginDevice != null && loginDevice < 5) {
                    String token = UUID.randomUUID().toString();
                    LoginBo loginBo = new LoginBo(user.getId(), loginBySmsVerifyDto.getImei(), token);
                    userRedisMapper.updateToken(loginBo);
                    userRedisMapper.unlockLogin(loginBo.getId());
                    return new LoginVo(user.getId(), token);
                }
            }
            throw new LoginException();
        }
        throw new IncorrectSmsVerifyCodeException();
    }

    @Override
    public void sendRegisterSmsVerifyCode(@NotNull SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        if (userRedisMapper.selectRegisterSmsVerifyCodeByPhone(sendSmsVerifyCodeDto.getPhone()) == null) {
            int smsVerifyCode = (int) ((Math.random() * 9 + 1) * 10000);
            if (userRedisMapper.updateRegisterSmsVerifyCode(sendSmsVerifyCodeDto.getPhone(), smsVerifyCode)) {
                try {
                    System.out.println("发送");
                    return;
                } catch (Throwable throwable) {
                    userRedisMapper.deleteRegisterSmsVerifyCode(sendSmsVerifyCodeDto.getPhone());
                    throw new SendSmsVerifyCodeException();
                }
            }
        }
        throw new FrequentlySendSmsVerifyCodeException();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public void register(@NotNull RegisterDto registerDto) {
        if (registerDto.getSmsVerifyCode().equals(userRedisMapper.selectRegisterSmsVerifyCodeByPhone(registerDto.getPhone()))) {
            if (userMySQLMapper.existByPhone(registerDto.getPhone()) == 0) {
                long id = idWorker.nextId();
                User user = new User();
                user.setId(id);
                user.setPhone(registerDto.getPhone());
                user.setNickname(md5Cipher.md5Hash16(Long.toString(id)));
                user.setPassword(registerDto.getPassword());
                if (userMySQLMapper.insert(user) != 1) {
                    throw new RegisterException();
                }
                return;
            }
            throw new RepeatedlyRegisterException();
        }
        throw new IncorrectSmsVerifyCodeException();
    }

    @Override
    public void logout(@NotNull LogoutDto logoutDto) {
        if (userRedisMapper.lockLogin(logoutDto.getId())) {
            if (userRedisMapper.selectToken(logoutDto.getId(), logoutDto.getImei()) != null) {
                userRedisMapper.deleteToken(logoutDto.getId(), logoutDto.getImei());
            }
            userRedisMapper.unlockLogin(logoutDto.getId());
        }
    }

    @Override
    public void sendUpdatePasswordSmsVerifyCode(@NotNull SendSmsVerifyCodeDto sendSmsVerifyCodeDto) {
        if (userRedisMapper.selectUpdatePasswordSmsVerifyCodeByPhone(sendSmsVerifyCodeDto.getPhone()) == null) {
            int smsVerifyCode = (int) ((Math.random() * 9 + 1) * 10000);
            if (userRedisMapper.updateUpdatePasswordSmsVerifyCode(sendSmsVerifyCodeDto.getPhone(), smsVerifyCode)) {
                try {
                    System.out.println("发送");
                    return;
                } catch (Throwable throwable) {
                    userRedisMapper.deleteUpdatePasswordSmsVerifyCode(sendSmsVerifyCodeDto.getPhone());
                    throw new SendSmsVerifyCodeException();
                }
            }
        }
        throw new FrequentlySendSmsVerifyCodeException();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public void updatePassword(@NotNull UpdatePasswordDto updatePasswordDto) {
        if (updatePasswordDto.getSmsVerifyCode().equals(userRedisMapper.selectRegisterSmsVerifyCodeByPhone(updatePasswordDto.getPhone()))) {
            UpdatePasswordBo updatePasswordBo = new UpdatePasswordBo();
            BeanUtils.copyProperties(updatePasswordDto, updatePasswordBo);
            for (int times = 0; times < 20; times++) {
                updatePasswordBo.setVersion(userMySQLMapper.selectVersionByPhone(updatePasswordDto.getPhone()));
                if (userMySQLMapper.updatePassword(updatePasswordBo) == 1) {
                    return;
                }
            }
            throw new UpdatePasswordException();
        }
        throw new IncorrectSmsVerifyCodeException();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public void updateDetail(@NotNull UpdateDetailDto updateDetailDto) {
        UpdateDetailBo updateDetailBo = new UpdateDetailBo();
        BeanUtils.copyProperties(updateDetailDto, updateDetailBo);
        for (int times = 0; times < 20; times++) {
            updateDetailBo.setVersion(userMySQLMapper.selectVersionById(updateDetailBo.getId()));
            if (userMySQLMapper.updateDetail(updateDetailBo) == 1) {
                return;
            }
        }
        throw new UpdateDetailException();
    }
}