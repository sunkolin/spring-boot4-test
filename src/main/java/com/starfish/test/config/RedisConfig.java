package com.starfish.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

/**
 * RedisConfig
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2026-01-14
 */
@Configuration
public class RedisConfig {

    /**
     * 自定义 RedisTemplate<String, Object> Bean
     * 解决Bean找不到问题，同时配置序列化器避免数据乱码
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 1. 创建RedisTemplate对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 2. 关联Redis连接工厂（由SpringBoot自动配置，直接注入使用）
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 3. 配置序列化器（核心：解决Redis存储数据乱码问题）
        // 3.1 字符串序列化器（用于key的序列化，推荐）
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 3.2 JSON序列化器（用于value的序列化，支持复杂Java对象，推荐）
        GenericJacksonJsonRedisSerializer jsonRedisSerializer = new GenericJacksonJsonRedisSerializer(new ObjectMapper());

        // 4. 为不同类型的数据设置对应的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer); // key 序列化
        redisTemplate.setValueSerializer(jsonRedisSerializer); // value 序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer); // hash结构的key 序列化
        redisTemplate.setHashValueSerializer(jsonRedisSerializer); // hash结构的value 序列化

        // 5. 初始化RedisTemplate配置
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

}