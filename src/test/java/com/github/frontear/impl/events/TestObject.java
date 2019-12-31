package com.github.frontear.impl.events;

import com.github.frontear.efkolia.events.Listener;
import com.github.frontear.efkolia.events.Listener.Priority;
import com.github.frontear.internal.NotNull;
import java.util.concurrent.ThreadLocalRandom;

class TestObject {
    final boolean unregister;

    public TestObject() {
        this.unregister = ThreadLocalRandom.current().nextBoolean();
    }

    @Listener(Priority.HIGH)
    private void onTestHigh(@NotNull final TestEvent event) {
        event.logger.info("HIGH!");
        if (unregister) {
            event.executor.unregister(this);
        }
    }

    @Listener(Priority.NORMAL)
    private void onTestNormal(@NotNull final TestEvent event) {
        event.logger.info("NORMAL!");
    }

    @Listener(Priority.LOW)
    private void onTestLow(@NotNull final TestEvent event) {
        event.logger.info("LOW!");
    }
}
