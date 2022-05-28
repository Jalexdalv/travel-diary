package com.huanshi.traveldiary.interceptor;

import com.huanshi.traveldiary.common.exception.ImeiException;
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
public class ImeiInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRedisMapper userRedisMapper;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull Object handler) {
        httpServletRequest.setAttribute("imei", StringUtils.trimToNull(httpServletRequest.getHeader("imei")));
        if (Pattern.matches("^[\\d]{15}(?:[\\d]{2})?$", (String) httpServletRequest.getAttribute("imei"))) {
            httpServletRequest.setAttribute("imei", Long.parseLong((String) httpServletRequest.getAttribute("imei")));
            return true;
        }
        throw new ImeiException();
    }
}