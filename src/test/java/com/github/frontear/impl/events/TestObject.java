package com.github.frontear.impl.events;

import com.github.frontear.efkolia.events.Listener;
import com.github.frontear.efkolia.events.Listener.Priority;
import com.github.frontear.efkolia.utilities.randomizer.PseudoRandom;
import com.github.frontear.internal.NotNull;

class TestObject {
    final boolean unregister;
    final boolean throwing;

    public TestObject() {
        this.unregister = PseudoRandom.nextBoolean();
        this.throwing = PseudoRandom.nextBoolean();
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
            throw new RuntimeException();
        }
    }

    @Listener(Priority.LOW)
    private void onTestLow(@NotNull final TestEvent event) {
        event.logger.info("LOW!");
    }
}
