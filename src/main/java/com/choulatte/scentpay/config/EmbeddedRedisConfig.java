package com.choulatte.scentpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfig {
    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public RedisServer embeddedRedis() {
        return RedisServer.builder().port(redisPort).build();
    }
}
