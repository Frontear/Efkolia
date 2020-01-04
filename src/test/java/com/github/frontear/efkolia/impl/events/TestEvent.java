package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.github.frontear.internal.NotNull;

class TestEvent extends Event {
    EventExecutor executor;
    Logger logger;

    TestEvent(@NotNull final EventExecutor executor, @NotNull final MinecraftMod mod) {
        this.executor = executor;
        this.logger = mod.getLogger("Event");
    }
}
