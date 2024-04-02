package ru.hse.softwear.cinemaworld.restServer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {

    private Map<String, DatabaseConfig> databases = new HashMap<>();

    @Data
    public static class DatabaseConfig{
        private String host;
        private Integer port;
    }
}
