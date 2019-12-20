package com.github.frontear.impl.events;

import com.github.frontear.internal.NotNull;

class TestEvent extends Event {
    String string;
    int number;

    public TestEvent(@NotNull final String string, final int number) {
        this.string = string;
        this.number = number;
    }
}
