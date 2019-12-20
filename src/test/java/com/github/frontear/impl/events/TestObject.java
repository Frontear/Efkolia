package com.github.frontear.impl.events;

import static com.github.frontear.impl.events.EventExecutorTest.cancel_normal;

import com.github.frontear.efkolia.events.Listener;
import com.github.frontear.efkolia.events.Listener.Priority;
import com.github.frontear.internal.NotNull;

class TestObject {
    @Listener(Priority.HIGH)
    private void onTestHigh(@NotNull final TestEvent event) {
        event.string = "HIGH";
        event.number = 0;
    }

    @Listener(Priority.NORMAL)
    private void onTestNormal(@NotNull final TestEvent event) {
        if (cancel_normal) {
            event.cancel();
        }

        event.string = "NORMAL";
        event.number = 1;
    }

    @Listener(Priority.LOW)
    private void onTestLow(@NotNull final TestEvent event) {
        event.string = "LOW";
        event.number = 2;
    }
}
