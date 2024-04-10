package ru.hse.softwear.cinemaworld.userServer.service.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.cypher.OrderTokenCypher;
import ru.hse.softwear.cinemaworld.userServer.service.RedisService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Component
public class ClearSessionTokenScheduler implements Runnable {

    @Autowired
    private RedisService redisService;

    @Override
    public void run() {
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
