package com.huanshi.traveldiary.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = "classpath:md5-cipher.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "md5-cipher")
@Component
public class Md5Cipher {
    @Value("${md5-cipher.salt}")
    private String salt;

    @NotNull
    public String md5Hash32(@NotNull String data) {
        return DigestUtils.md5Hex(data + salt);
    }

    @NotNull
    public String md5Hash16(@NotNull String data) {
        return StringUtils.substring(md5Hash32(data), 8, 24);
    }
}