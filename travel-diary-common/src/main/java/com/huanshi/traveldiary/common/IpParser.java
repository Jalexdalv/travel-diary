package com.huanshi.traveldiary.common;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@PropertySource(value = "classpath:ip-parser.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "ip-parser")
@Component
public class IpParser {
    @Value("${ip-parser.header}")
    private List<String> headerList;

    @NotNull
    public String parse(@NotNull HttpServletRequest httpServletRequest) {
        if (headerList != null) {
            for (String header : headerList) {
                String ip = StringUtils.trimToNull(httpServletRequest.getHeader(header));
                if (ip != null && !StringUtils.equalsIgnoreCase("unknown", ip)) {
                    if (StringUtils.contains(ip, ",")) {
                        for (String part : StringUtils.split(ip, ",")) {
                            if (!StringUtils.equalsIgnoreCase("unknown", part)) {
                                return part;
                            }
                        }
                    }
                    return ip;
                }
            }
        }
        return httpServletRequest.getRemoteAddr();
    }
}