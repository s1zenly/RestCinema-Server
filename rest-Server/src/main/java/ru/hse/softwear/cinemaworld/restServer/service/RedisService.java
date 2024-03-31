package ru.hse.softwear.cinemaworld.restServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplateRefreshToken;
    private final RedisTemplate<Object, Object> redisTemplateCacheOrderSession;

    @Autowired
    public RedisService(RedisConnectionFactory redisConnectionFactoryRefreshToken,
                        RedisConnectionFactory redisConnectionFactoryCacheOrderSession) {
        this.redisTemplateRefreshToken = new RedisTemplate<>();
        this.redisTemplateCacheOrderSession = new RedisTemplate<>();

        this.redisTemplateRefreshToken.setConnectionFactory(redisConnectionFactoryRefreshToken);
        this.redisTemplateCacheOrderSession.setConnectionFactory(redisConnectionFactoryCacheOrderSession);
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
