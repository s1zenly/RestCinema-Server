package ru.hse.softwear.cinemaworld.userServer.configuration;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.hse.softwear.cinemaworld.userServer.serializer.OccupiedPlaceSerializer;
import ru.hse.softwear.cinemaworld.userServer.view.model.OccupiedPlace;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;

    @Bean(name = "redisTemplateRefreshToken")
    public RedisTemplate<String, String> redisTemplateRefreshToken() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(createConnectionFactory(0));

        return redisTemplate;
    }

    @Bean(name = "redisTemplateCacheOrderSession")
    public RedisTemplate<String, List<OccupiedPlace>> redisTemplateCacheOrderSession() {
        RedisTemplate<String, List<OccupiedPlace>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new OccupiedPlaceSerializer());
        redisTemplate.setConnectionFactory(createConnectionFactory(1));

        return redisTemplate;
    }


    private JedisConnectionFactory createConnectionFactory(int numberDatabase) {
        RedisProperties.DatabaseConfig dbConfig = redisProperties.getDatabases().get(String.valueOf(numberDatabase));
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(dbConfig.getHost(), dbConfig.getPort());
        redisStandaloneConfiguration.setDatabase(numberDatabase);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }
}
