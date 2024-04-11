package ru.hse.softwear.cinemaworld.userServer.service;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.service.scheduler.ClearSessionTokenScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SchedulerService implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ApplicationContext context;
    private static final int count = 2;
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(count);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        run();
    }

    public void run() {
        log.info("Daemon-threads starts");
        executorService.scheduleAtFixedRate(new ClearSessionTokenScheduler(context), 0, 20, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void exit() {
        executorService.shutdown();
    }
}
