package ru.hse.softwear.cinemaworld.restServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplateRefreshToken;
    private final RedisTemplate<Object, Object> redisTemplateCacheOrderSession;

    @Autowired
    public RedisService(@Qualifier("redisTemplateRefreshToken") RedisTemplate<String, String> redisTemplateRefreshToken,
                        @Qualifier("redisTemplateCacheOrderSession") RedisTemplate<Object, Object> redisTemplateCacheOrderSession) {
        this.redisTemplateRefreshToken = redisTemplateRefreshToken;
        this.redisTemplateCacheOrderSession = redisTemplateCacheOrderSession;
    }

    public void setInRefreshToken(String key, String value) {
        redisTemplateRefreshToken.opsForValue().set(key, value);
    }

    public void setInCacheOrdersSession(Object key, Object value) {
        redisTemplateCacheOrderSession.opsForValue().set(key, value);
    }

    public String getInRefreshToken(String key) {
        return redisTemplateRefreshToken.opsForValue().get(key);
    }

    public Object getInCacheOrdersSession(Object key) {
        return redisTemplateCacheOrderSession.opsForValue().get(key);
    }
}
