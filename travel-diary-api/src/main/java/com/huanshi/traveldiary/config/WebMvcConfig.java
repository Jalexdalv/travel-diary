package com.huanshi.traveldiary.config;

import com.huanshi.traveldiary.interceptor.GlobalInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private GlobalInterceptor globalInterceptor;

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(globalInterceptor)
                .addPathPatterns("/user/update-nickname")
                .addPathPatterns("/user/update-sex")
                .addPathPatterns("/user/update-avatar")
                .addPathPatterns("/user/update-profile");
        WebMvcConfigurer.super.addInterceptors(interceptorRegistry);
    }
}