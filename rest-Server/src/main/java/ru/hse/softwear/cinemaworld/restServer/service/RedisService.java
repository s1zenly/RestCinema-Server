package ru.hse.softwear.cinemaworld.restServer.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;

import javax.persistence.SecondaryTable;
import java.util.List;
import java.util.Set;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplateRefreshToken;
    private final RedisTemplate<String, List<OccupiedPlace>> redisTemplateCacheOrderSession;

    public RedisService(@Qualifier("redisTemplateRefreshToken") RedisTemplate<String, String> redisTemplateRefreshToken,
                        @Qualifier("redisTemplateCacheOrderSession") RedisTemplate<String, List<OccupiedPlace>> redisTemplateCacheOrderSession) {

        this.redisTemplateRefreshToken = redisTemplateRefreshToken;
        this.redisTemplateCacheOrderSession = redisTemplateCacheOrderSession;
    }

    public void setInRefreshToken(String key, String value) {
        redisTemplateRefreshToken.opsForValue().set(key, value);
    }

    public void setInCacheOrdersSession(String key, List<OccupiedPlace> value) {
        redisTemplateCacheOrderSession.opsForValue().set(key, value);
    }

    public String getInRefreshToken(String key) {
        return redisTemplateRefreshToken.opsForValue().get(key);
    }

    public List<OccupiedPlace> getInCacheOrdersSession(String key) {
        return redisTemplateCacheOrderSession.opsForValue().get(key);
    }

    public void deleteInCacheOrderSession(String key) {
        redisTemplateCacheOrderSession.delete(key);
    }

    public Set<String> allKeysInCacheOrderSession() {
        return redisTemplateCacheOrderSession.keys("*");
    }
}
