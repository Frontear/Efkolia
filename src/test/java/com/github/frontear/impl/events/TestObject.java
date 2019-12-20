package com.github.frontear.impl.events;

import com.github.frontear.efkolia.events.Listener;
import com.github.frontear.internal.NotNull;

class TestObject {
    @Listener
    private void onTest(@NotNull final TestEvent event) {
        event.string += " Modified";
        event.number *= 321;
    }
}
