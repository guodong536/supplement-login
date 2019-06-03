package com.pingan.pare.bi.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingan.pare.bi.common.common.ObjectRedisSerializer;
import com.pingan.pare.bi.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {

    private int duration=30;

    @Bean
    public RedisCacheConfiguration defaultCacheConfig(){
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        log.info("{}",duration);
        RedisCacheConfiguration config= RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(duration))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new ObjectRedisSerializer()));
        return config;
    }

    /**
     * 设置redisCacheManager
     * 使用cache注解管理redis缓存
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){
        RedisCacheManager cm=RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultCacheConfig())
                .transactionAware()
                .build();
        return cm;
    }

    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template =new StringRedisTemplate(factory);
        template.setValueSerializer(new ObjectRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        log.info("init success");
        return template;
    }

    @Bean
    public RedisUtil redisUtil(@Qualifier("redisTemplate")RedisTemplate redisTemplate){
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}
