package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import lombok.NonNull;

class TestEvent extends Event {
    EventExecutor executor;
    Logger logger;

    TestEvent(@NonNull final EventExecutor executor, @NonNull final MinecraftMod mod) {
        this.executor = executor;
        this.logger = mod.getLogger("Event");
    }
}
