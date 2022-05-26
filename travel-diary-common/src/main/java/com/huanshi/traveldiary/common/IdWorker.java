package com.huanshi.traveldiary.common;

import com.huanshi.traveldiary.common.exception.InitializeIdWorkerException;
import com.huanshi.traveldiary.common.exception.SystemTimeRewindException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@PropertySource(value = "classpath:id-worker.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "id-worker")
@Component
public class IdWorker {
    @Value("${id-worker.start-stamp}")
    private long startStamp;
    @Value("${id-worker.datacenter}")
    private int dataCenter;
    @Value("${id-worker.machine}")
    private int machine;
    private long sequence = 0L;
    private long lastStamp = -1L;

    @PostConstruct
    private void init() {
        if (dataCenter < 0 || dataCenter > 31 || machine < 0 || machine > 31) {
            throw new InitializeIdWorkerException();
        }
    }

    public synchronized long nextId() {
        long currentStamp = System.currentTimeMillis();
        if (currentStamp < lastStamp) {
            throw new SystemTimeRewindException();
        } else if (currentStamp == lastStamp) {
            sequence = (sequence + 1) & 4095L;
            if (sequence == 0L) {
                currentStamp = System.currentTimeMillis();
                while (currentStamp <= lastStamp) {
                    currentStamp = System.currentTimeMillis();
                }
            }
        } else {
            sequence = 0L;
        }
        lastStamp = currentStamp;
        return (currentStamp - startStamp) << 22L | (long) dataCenter << 17L | (long) machine << 12L | sequence;
    }
}