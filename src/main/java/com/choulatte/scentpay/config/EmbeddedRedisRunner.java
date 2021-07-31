package com.choulatte.scentpay.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

@Component
@RequiredArgsConstructor
public class EmbeddedRedisRunner implements ApplicationRunner, DisposableBean {

    private final RedisServer redisServer;

    @Override
    public void run(ApplicationArguments args) {
        redisServer.start();
    }

    @Override
    public void destroy() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
