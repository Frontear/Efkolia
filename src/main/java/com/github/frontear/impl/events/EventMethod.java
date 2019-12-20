package com.github.frontear.impl.events;

import com.github.frontear.efkolia.events.Listener;
import com.github.frontear.efkolia.events.Listener.Priority;
import com.github.frontear.internal.NotNull;
import java.lang.reflect.*;
import lombok.*;

@EqualsAndHashCode
final class EventMethod implements Comparable<EventMethod> {
    final Object instance;
    private final Method method;
    private final Priority priority;

    EventMethod(final Method method, final Object instance) {
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

    @Override
    public String toString() {
        return instance.getClass().getSimpleName() + "#" + method.getName() + "()";
    }
}
