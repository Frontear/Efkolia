package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import java.lang.reflect.Method;
import lombok.*;

@EqualsAndHashCode
final class EventMethod implements Comparable<EventMethod> {
    private final Object instance;
    private final Method callback;
    private final Priority priority;

    public EventMethod(@NonNull final Object instance, @NonNull final Method callback) {
        this.instance = instance;
        (this.callback = callback).setAccessible(true);
        this.priority = callback.getAnnotation(Listener.class).value();
    }

    public void invoke(@NonNull final Event event) throws Exception {
        callback.invoke(instance, event);
    }

    @Override
    public int compareTo(@NonNull final EventMethod other) {
        return other.priority.compareTo(this.priority);
    }

    public boolean isFrom(@NonNull final Object instance) {
        return this.instance == instance;
    }
}
