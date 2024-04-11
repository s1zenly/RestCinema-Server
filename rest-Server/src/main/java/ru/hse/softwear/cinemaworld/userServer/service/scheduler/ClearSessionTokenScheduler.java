package ru.hse.softwear.cinemaworld.userServer.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.cypher.OrderTokenCypher;
import ru.hse.softwear.cinemaworld.userServer.service.RedisService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Slf4j
@Component
public class ClearSessionTokenScheduler implements Runnable {


    private final RedisService redisService;

    @Autowired
    public ClearSessionTokenScheduler(ApplicationContext context) {
        this.redisService = context.getBean(RedisService.class);
    }

    @Override
    public void run() {
        log.info("Start clear order thread");
        Set<String> tokens = redisService.allKeysInCacheOrderSession();

        for(String token : tokens) {
            try {
                LocalDateTime timeToken = (LocalDateTime) OrderTokenCypher.decoder(token).get("date");
                LocalDateTime now = LocalDateTime.now();

                if(ChronoUnit.MINUTES.between(timeToken, now) >= 10) {
                    redisService.deleteInCacheOrderSession(token);
                }
            } catch (Exception ignore) {}
        }
    }
}
