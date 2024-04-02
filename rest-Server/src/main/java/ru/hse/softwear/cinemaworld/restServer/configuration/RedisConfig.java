package ru.hse.softwear.cinemaworld.restServer.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.hse.softwear.cinemaworld.restServer.serializer.OccupiedPlaceSerializer;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisConnectionFactory redisConnectionFactoryRefreshToken;
    private final RedisConnectionFactory redisConnectionFactoryCacheOrderSession;

    @Bean(name = "redisTemplateRefreshToken")
    public RedisTemplate<String, String> redisTemplateRefreshToken() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactoryRefreshToken);

        return redisTemplate;
    }

    @Bean(name = "redisTemplateCacheOrderSession")
    public RedisTemplate<String, List<OccupiedPlace>> redisTemplateCacheOrderSession() {
        RedisTemplate<String, List<OccupiedPlace>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new OccupiedPlaceSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactoryCacheOrderSession);

        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactoryCacheOrderSession);
        return redisTemplate;
    }
}
