package com.huanshi.traveldiary.interceptor;

import com.huanshi.traveldiary.common.exception.AuthenticateException;
import com.huanshi.traveldiary.mapper.redis.UserRedisMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Component
public class AuthenticateInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRedisMapper userRedisMapper;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull Object handler) {
        httpServletRequest.setAttribute("id", StringUtils.trimToNull(httpServletRequest.getHeader("id")));
        long imei = (long) httpServletRequest.getAttribute("imei");
        String token = StringUtils.trimToNull(httpServletRequest.getHeader("token"));
        if (Pattern.matches("^[\\d]{18}$", (String) httpServletRequest.getAttribute("id")) && token != null) {
            httpServletRequest.setAttribute("id", Long.parseLong((String) httpServletRequest.getAttribute("id")));
            if (token.equals(userRedisMapper.selectToken(imei, (long) httpServletRequest.getAttribute("id")))) {
                return true;
            }
        }
        throw new AuthenticateException();
    }
}