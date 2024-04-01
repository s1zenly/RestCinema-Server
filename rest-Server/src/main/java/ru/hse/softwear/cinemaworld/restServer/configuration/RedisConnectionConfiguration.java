package ru.hse.softwear.cinemaworld.restServer.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@RequiredArgsConstructor
public class RedisConnectionConfiguration {

    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactoryRefreshToken() {
        return createConnectionFactory(0);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactoryCacheOrderSession() {
        return createConnectionFactory(1);
    }


    private RedisConnectionFactory createConnectionFactory(int numberDatabase) {
        RedisProperties.DatabaseConfig dbConfig = redisProperties.getDatabases().get(String.valueOf(numberDatabase));
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(dbConfig.getHost(), dbConfig.getPort());

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}
