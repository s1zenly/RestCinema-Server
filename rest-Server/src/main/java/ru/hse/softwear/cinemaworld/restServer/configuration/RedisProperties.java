package ru.hse.softwear.cinemaworld.restServer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {

    private Map<String, DatabaseConfig> databases = new HashMap<>();

    @Data
    public static class DatabaseConfig{
        private String host;
        private Integer port;
    }
}
