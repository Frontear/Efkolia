package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.internal.NotNull;
import java.lang.reflect.*;
import java.util.function.Consumer;
import lombok.*;

// todo: fix generics
@EqualsAndHashCode
final class EventMethod implements Comparable<EventMethod> {
    final Object instance;
    final Consumer<Event> listener;
    private final Priority priority;
    boolean remove;

    EventMethod(@NotNull final Method method, @NotNull final Object instance) {
        method.setAccessible(true);
        this.listener = new Consumer<Event>() {
            @Override
            @SneakyThrows({ IllegalAccessException.class, InvocationTargetException.class })
            public void accept(final Event event) {
                method.invoke(instance, event);
            }
        };
        this.instance = instance;
        this.priority = method.getAnnotation(Listener.class).value();
    }

    EventMethod(@NotNull final Consumer<Event> listener) {
        this.instance = null;
        this.listener = listener;
        this.priority = Priority.NORMAL; // todo: custom priority?
    }

    void invoke(@NotNull final Event event) {
        listener.accept(event);
    }

    @Override
    public int compareTo(@NotNull final EventMethod other) {
        return other.priority.compareTo(this.priority);
    }
}
