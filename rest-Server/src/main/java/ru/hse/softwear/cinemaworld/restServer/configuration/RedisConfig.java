package ru.hse.softwear.cinemaworld.restServer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    private List<DatabaseConfig> databases;

    @Data
    public static class DatabaseConfig{
        private String host;
        private Integer port;
        private String password;
        private Integer database;
    }
}
