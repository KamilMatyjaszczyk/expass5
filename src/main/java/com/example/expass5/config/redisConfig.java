package com.example.expass5.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.UnifiedJedis;

@Configuration
public class redisConfig {
    @Bean
    public UnifiedJedis jedisClient() {
        return new UnifiedJedis("redis://localhost:6379");
    }

}
