package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import lombok.NonNull;

class TestObject {
    final boolean throwing;
    final boolean unregister;

    public TestObject() {
        this.throwing = LocalRandom.nextBoolean();
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
