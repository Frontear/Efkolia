package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.github.frontear.internal.NotNull;
import java.lang.ref.*;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;
import lombok.*;

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
            logger.debug("Registering %s", type.getSimpleName());
            val methods = Arrays.stream(type.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(Listener.class))
                .filter(x -> Modifier.isPrivate(x.getModifiers()))
                .filter(x -> x.getReturnType() == Void.TYPE).filter(x -> x.getParameterCount() == 1)
                .filter(x -> Event.class.isAssignableFrom(x.getParameterTypes()[0]));

            methods.forEach(x -> {
                val key = x.getParameterTypes()[0].asSubclass(Event.class);
                val value = new EventMethod<>(instance, x);
                logger.debug("Adding a listener for %s", key.getSimpleName());

                listeners.putIfAbsent(key, new TreeSet<>());
                listeners.get(key).add(value);
            });
        }
        while ((type = type.getSuperclass()) != Object.class);
    }

    @Override
    public <E1 extends Event> void register(@NonNull final Class<E1> event,
        @NonNull final Consumer<E1> listener) {
        logger.debug("Registering %s to %s", listener, event.getSimpleName());

        listeners.putIfAbsent(event, new TreeSet<>());
        listeners.get(event).add(new EventMethod<>(listener));
    }

    @Override
    public void unregister(@NonNull final Object instance) {
        logger.debug("Unregistering %s", instance.getClass().getSimpleName());

        listeners.forEach((k, v) -> v.removeIf(x -> x.hash == instance.hashCode()));
    }

    @Override
    public <E1 extends Event> void unregister(@NonNull final Class<E1> event,
        @NonNull final Consumer<E1> listener) {
        logger.debug("Unregistering %s from %s", listener, event);

        if (listeners.containsKey(event)) {
            listeners.get(event).removeIf(x -> x.hash == listener.hashCode());
        }
    }

    @NotNull
    @Override
    public <E1 extends Event> E1 fire(@NonNull final E1 event) {
        val key = event.getClass();
        logger.debug("Firing listeners for %s", key.getSimpleName());

        if (listeners.containsKey(key)) {
            val methods = new WeakReference<>(
                new TreeSet<>(listeners.get(key))); // todo: memory impact
            //noinspection ConstantConditions
            for (val method : methods.get()) {
                try {
                    method.invoke(event);
                }
                catch (final Exception e) {
                    logger.debug("%s failed with an exception %s", method,
                        e.getClass().getSimpleName());
                    e.printStackTrace();
                }

                if (event.isCancelled()) {
                    logger.debug("Event firing cancelled");
                    break;
                }
            }
        }
        else {
            logger.debug("No listeners found");
        }

        return event;
    }
}
