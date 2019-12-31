package com.github.frontear.efkolia.impl.events;

import lombok.Getter;

public abstract class Event {
    @Getter private boolean cancelled;

    public final void cancel() {
        this.cancelled = true;
    }
}
