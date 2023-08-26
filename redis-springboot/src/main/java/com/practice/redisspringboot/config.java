package com.practice.redisspringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class config {
    @Bean
    LettuceConnectionFactory lettuceConnectionFactory(){
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(
                new RedisStandaloneConfiguration("localhost",6379)
        );

        return  connectionFactory;
    }
    @Bean
    RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> template= new RedisTemplate<>();
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

        template.setKeySerializer(stringRedisSerializer);

        JdkSerializationRedisSerializer jdkSerializationRedisSerializer= new JdkSerializationRedisSerializer();
        template.setValueSerializer(jdkSerializationRedisSerializer);
        template.setHashValueSerializer(jdkSerializationRedisSerializer);

        template.setConnectionFactory(lettuceConnectionFactory());
        template.afterPropertiesSet();


        return  template;
    }


}
