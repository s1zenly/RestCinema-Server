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

    private final RedisConfig redisConfig;

    @Bean
    public RedisConnectionFactory redisConnectionFactoryRefreshToken() {
        return createConnectionFactory(0);
    }

    @Bean RedisConnectionFactory redisConnectionFactoryCacheOrderSession() {
        return createConnectionFactory(1);
    }


    private RedisConnectionFactory createConnectionFactory(int numberDatabase) {
        RedisConfig.DatabaseConfig dbConfig = redisConfig.getDatabases().get(numberDatabase);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(dbConfig.getHost(), dbConfig.getPort());
        redisStandaloneConfiguration.setPassword(dbConfig.getPassword());
        redisStandaloneConfiguration.setDatabase(dbConfig.getDatabase());

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}
