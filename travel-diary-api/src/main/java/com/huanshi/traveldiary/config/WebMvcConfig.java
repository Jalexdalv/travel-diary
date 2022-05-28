package com.huanshi.traveldiary.config;

import com.huanshi.traveldiary.interceptor.AuthenticateInterceptor;
import com.huanshi.traveldiary.interceptor.ImeiInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ImeiInterceptor imeiInterceptor;
    @Autowired
    private AuthenticateInterceptor authenticateInterceptor;

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(imeiInterceptor)
                .addPathPatterns("/user/password-login")
                .addPathPatterns("/user/sms-verify-login")
                .addPathPatterns("/user/logout")
                .order(1);
        interceptorRegistry.addInterceptor(authenticateInterceptor)
                .addPathPatterns("/user/logout")
                .addPathPatterns("/user/update-detail")
                .order(2);
        WebMvcConfigurer.super.addInterceptors(interceptorRegistry);
    }
}