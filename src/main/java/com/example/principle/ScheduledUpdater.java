package com.example.principle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledUpdater {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private long initialDelayInSeconds;
    private long periodInSeconds;
    private Updater updater;

    public ScheduledUpdater(Updater updater,long initialDelayInSeconds, long periodInSeconds ) {
        this.updater = updater;
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.periodInSeconds = periodInSeconds;
    }


    public void run() {
        executor.scheduleAtFixedRate(
                () -> {
                    updater.update();
                },
                initialDelayInSeconds, periodInSeconds, TimeUnit.SECONDS);
    }
}
