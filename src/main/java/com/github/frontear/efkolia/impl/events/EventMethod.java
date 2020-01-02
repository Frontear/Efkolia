package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.internal.NotNull;
import java.lang.reflect.*;
import lombok.*;

@EqualsAndHashCode
final class EventMethod implements Comparable<EventMethod> {
    final Object instance;
    private final Method method;
    private final Priority priority;
    boolean remove;

    EventMethod(@NotNull final Method method, @NotNull final Object instance) {
        (this.method = method).setAccessible(true);
        this.instance = instance;
        this.priority = method.getAnnotation(Listener.class).value();
    }

    @SneakyThrows({ IllegalAccessException.class, InvocationTargetException.class })
    void invoke(@NotNull final Event event) {
        method.invoke(instance, event);
    }

    @Override
    public int compareTo(@NotNull final EventMethod other) {
        return other.priority.compareTo(this.priority);
    }

    @NotNull
    @Override
    public String toString() {
        return instance.getClass().getSimpleName() + "#" + method.getName() + "()";
    }
}
