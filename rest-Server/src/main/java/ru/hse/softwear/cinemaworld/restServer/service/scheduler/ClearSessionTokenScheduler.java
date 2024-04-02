package ru.hse.softwear.cinemaworld.restServer.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hse.softwear.cinemaworld.restServer.cypher.OrderTokenCypher;
import ru.hse.softwear.cinemaworld.restServer.service.RedisService;

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
