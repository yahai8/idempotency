package com.cn.config;

import com.cn.annotation.ApiIdempotent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author: MuYaHai
 * Date: 2019/12/15, Time: 17:59
 */
//redispool配置类
@Configuration
public class RedisCacheConfiguration {

    @Value("${spring.redis.host}")
    String host;
    @Value("${spring.redis.port}")
    String port;

    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool(host, Integer.parseInt(port));
        return jedisPool;
    }
}
