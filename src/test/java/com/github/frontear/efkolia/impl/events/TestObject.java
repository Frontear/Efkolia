package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import lombok.NonNull;

class TestObject {
    static final boolean throwing;

    static {
        throwing = LocalRandom.nextBoolean();
    }

    final boolean unregister;

    public TestObject() {
        this.unregister = LocalRandom.nextBoolean();
    }

    @Listener(Priority.HIGH)
    private void onTestHigh(@NonNull final TestEvent event) {
        event.logger.info("HIGH!");
        if (unregister) {
            event.executor.unregister(this);
        }
    }

    @Listener(Priority.NORMAL)
    private void onTestNormal(@NonNull final TestEvent event) {
        event.logger.info("NORMAL!");
        if (throwing) {
            throw new TestEventException();
        }
    }

    @Listener(Priority.LOW)
    private void onTestLow(@NonNull final TestEvent event) {
        event.logger.info("LOW!");
    }
}
