package com.github.frontear.impl.events;

import com.github.frontear.impl.logging.Logger;
import com.github.frontear.internal.NotNull;

class TestEvent extends Event {
    EventExecutor executor;
    Logger logger;

    TestEvent(@NotNull final EventExecutor executor, @NotNull final Logger logger) {
        this.executor = executor;
        this.logger = logger.child("Event");
    }
}
