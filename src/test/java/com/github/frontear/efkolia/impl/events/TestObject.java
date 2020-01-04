package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import com.github.frontear.internal.NotNull;

class TestObject {
    final boolean unregister;
    final boolean throwing;

    public TestObject() {
        this.unregister = LocalRandom.nextBoolean();
        this.throwing = LocalRandom.nextBoolean();
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
        if (throwing) {
            throw new TestEventException();
        }
    }

    @Listener(Priority.LOW)
    private void onTestLow(@NotNull final TestEvent event) {
        event.logger.info("LOW!");
    }
}
