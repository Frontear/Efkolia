package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.internal.NotNull;
import java.lang.reflect.Modifier;
import java.util.*;
import lombok.val;

public final class EventExecutor implements IEventExecutor<Event> {
    private final Map<Class<? extends Event>, Set<EventMethod>> listeners = new HashMap<>();
    private final Logger logger;

    public EventExecutor(@NotNull final Logger logger) {
        this.logger = logger.child("EventExecutor");
    }

    @Override
    public void register(@NotNull final Object instance) {
        Class<?> type = instance.getClass();

        logger.debug("Registering %s", type.getSimpleName());
        do {
            logger.debug("Filtering methods");
            Arrays.stream(type.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(Listener.class))
                .filter(x -> Modifier.isPrivate(x.getModifiers()))
                .filter(x -> x.getReturnType() == Void.TYPE).filter(x -> x.getParameterCount() == 1)
                .filter(x -> Event.class.isAssignableFrom(x.getParameterTypes()[0])).forEach(x -> {
                val key = x.getParameterTypes()[0].asSubclass(Event.class);
                val value = new EventMethod(x, instance);

                logger.debug("Adding %s as a listener of %s", value, key.getSimpleName());

                listeners.putIfAbsent(key, new TreeSet<>());
                listeners.get(key).add(value);
            });
        }
        while ((type = type.getSuperclass())
            != Object.class); // register events hiding in the super classes
    }

    @Override
    public void unregister(@NotNull final Object instance) {
        logger.debug("Unregistering %s", instance.getClass().getSimpleName());
        listeners.forEach((k, v) -> v.stream().filter(x -> x.instance.equals(instance)).forEach(
            EventMethod::flag));
    }

    @Override
    public <E1 extends Event> E1 fire(@NotNull final E1 event) {
        val key = event.getClass();

        logger.debug("Firing listeners for %s", key.getSimpleName());
        if (listeners.containsKey(key)) {
            val set = listeners.get(key);
            for (val listener : set) {
                logger.debug("Invoking %s", listener);
                try {
                    listener.invoke(event);
                }
                catch (@NotNull final Exception e) {
                    logger.debug("Invocation failed");
                    e.printStackTrace();
                }

                if (event.isCancelled()) {
                    logger.debug("Event cancelled");
                    break;
                }
            }

            set.removeIf(x -> x.remove);
        }
        else {
            logger.debug("No listeners found");
        }

        return event;
    }
}
