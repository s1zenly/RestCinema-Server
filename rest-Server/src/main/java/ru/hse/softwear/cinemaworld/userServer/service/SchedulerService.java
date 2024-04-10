package ru.hse.softwear.cinemaworld.userServer.service;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.service.scheduler.ClearSessionTokenScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService implements ApplicationListener<ApplicationReadyEvent> {

    private static final int count = 2;
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(count);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        run();
    }

    public void run() {
        executorService.scheduleAtFixedRate(new ClearSessionTokenScheduler(), 0, 20, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void exit() {
        executorService.shutdown();
    }
}
