package com.huanshi.traveldiary.interceptor;

import com.huanshi.traveldiary.common.IpParser;
import com.huanshi.traveldiary.mapper.redis.UserRedisMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    private IpParser ipParser;
    @Autowired
    private UserRedisMapper userRedisMapper;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull Object handler) {
        String id = StringUtils.trimToNull(httpServletRequest.getHeader("id"));
        String token = StringUtils.trimToNull(httpServletRequest.getHeader("token"));
        if (StringUtils.isNumeric(id) && token != null) {
            long _id = Long.parseLong(id);
            if (_id >= 100000000000000000L && _id <= 999999999999999999L) {
                if (token.equals(userRedisMapper.selectUserToken(_id))) {
                    return true;
                } else {
                    log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 " + _id + " 的请求 " + httpServletRequest.getRequestURI() + " 被拦截");
                    return false;
                }
            }
        }
        log.info("IP地址 " + ipParser.parse(httpServletRequest) + " 用户 " + id + " 的请求 " + httpServletRequest.getRequestURI() + " 格式不正确");
        return false;
    }
}