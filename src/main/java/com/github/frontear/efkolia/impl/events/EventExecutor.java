package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;
import lombok.*;

// todo: add logger entries
public final class EventExecutor implements IEventExecutor<Event> {
    private final Map<Class<? extends Event>, Set<EventMethod<?>>> listeners = new HashMap<>();
    private final Logger logger;

    public EventExecutor(@NonNull final MinecraftMod mod) {
        this.logger = mod.getLogger("EventExecutor");
    }

    @Override
    public void register(@NonNull final Object instance) {
        Class<?> type = instance.getClass();

        do {
            val methods = Arrays.stream(type.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(Listener.class))
                .filter(x -> Modifier.isPrivate(x.getModifiers()))
                .filter(x -> x.getReturnType() == Void.TYPE).filter(x -> x.getParameterCount() == 1)
                .filter(x -> Event.class.isAssignableFrom(x.getParameterTypes()[0]));
            methods.forEach(x -> {
                val key = x.getParameterTypes()[0].asSubclass(Event.class);
                val value = new EventMethod<>(instance, x);

                listeners.putIfAbsent(key, new TreeSet<>());
                listeners.get(key).add(value);
            });
        }
        while ((type = type.getSuperclass()) != Object.class);
    }

    @Override
    public void unregister(@NonNull final Object instance) {
        listeners.forEach((k, v) -> v.removeIf(x -> x.hash == instance.hashCode()));
    }

    @Override
    public <E1 extends Event> void register(@NonNull final Class<E1> event,
        @NonNull final Consumer<E1> listener) {
        listeners.putIfAbsent(event, new TreeSet<>());
        listeners.get(event).add(new EventMethod<>(listener));
    }

    @Override
    public <E1 extends Event> void unregister(@NonNull final Class<E1> event,
        @NonNull final Consumer<E1> listener) {
        if (listeners.containsKey(event)) {
            listeners.get(event).removeIf(x -> x.hash == listener.hashCode());
        }
    }

    @Override
    public <E1 extends Event> E1 fire(@NonNull final E1 event) {
        val key = event.getClass();

        if (listeners.containsKey(key)) {
            val methods = new TreeSet<>(listeners.get(key)); // todo: memory impact
            for (val method : methods) {
                try {
                    method.invoke(event);
                }
                catch (@NonNull final Exception e) {
                    e.printStackTrace();
                }

                if (event.isCancelled()) {
                    break;
                }
            }
        }

        return event;
    }
}
