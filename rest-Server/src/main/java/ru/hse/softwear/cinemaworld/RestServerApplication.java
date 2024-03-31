package ru.hse.softwear.cinemaworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.hse.softwear.cinemaworld.restServer.configuration.RedisConfig;

@SpringBootApplication
@EnableConfigurationProperties(RedisConfig.class)
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}

}
