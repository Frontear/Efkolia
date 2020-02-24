package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import lombok.*;

@EqualsAndHashCode
final class EventMethod<E extends Event> implements Comparable<EventMethod<?>> {
    final int hash;
    private final Consumer<Event> listener;
    private final Priority priority;

    public EventMethod(@NonNull final Object instance, @NonNull final Method method) {
        this.hash = instance.hashCode();
        this.listener = new Consumer<Event>() {
            @Override
            @SneakyThrows(ReflectiveOperationException.class)
            public void accept(final Event event) {
                method.invoke(instance, event);
            }
        };
        this.priority = method.getAnnotation(Listener.class).value();

        method.setAccessible(true);
    }

    public EventMethod(@NonNull final Consumer<E> listener) {
        this.hash = listener.hashCode();
        //noinspection unchecked
        this.listener = x -> listener.accept((E) x);
        this.priority = Priority.NORMAL;
    }

    public void invoke(@NonNull final Event event) {
        listener.accept(event);
    }

    @Override
    public int compareTo(@NonNull final EventMethod<?> other) {
        return other.priority.compareTo(this.priority);
    }
}
