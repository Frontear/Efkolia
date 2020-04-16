package com.github.frontear.efkolia.impl.events;

import com.github.frontear.efkolia.Properties;
import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.github.frontear.internal.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.util.*;
import lombok.*;

public final class EventExecutor implements IEventExecutor<Event> {
    private final Map<Class<? extends Event>, List<EventMethod>> listeners = new HashMap<>();
    private final Logger logger;

    public EventExecutor(@NonNull final MinecraftMod mod) {
        this.logger = mod.getLogger("EventExecutor");
    }

    @Override
    public void register(@NonNull final Object instance) {
        Class<?> type = instance.getClass();

        do {
            this.debug("Registering %s", type.getSimpleName());
            val methods = Arrays.stream(type.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(Listener.class))
                .filter(x -> Modifier.isPrivate(x.getModifiers()))
                .filter(x -> x.getReturnType() == Void.TYPE).filter(x -> x.getParameterCount() == 1)
                .filter(x -> Event.class.isAssignableFrom(x.getParameterTypes()[0]));

            methods.forEach(x -> {
                val key = x.getParameterTypes()[0].asSubclass(Event.class);
                val value = new EventMethod(instance, x);
                this.debug("Adding a listener for %s", key.getSimpleName());

                listeners.putIfAbsent(key, new ArrayList<>());
                listeners.get(key).add(value);
            });
        }
        while ((type = type.getSuperclass()) != Object.class);

        listeners.values().forEach(Collections::sort);
    }

    @Override
    public void unregister(@NonNull final Object instance) {
        this.debug("Unregistering %s", instance.getClass().getSimpleName());

        listeners.forEach((k, v) -> v.removeIf(x -> x.isFrom(instance)));

        listeners.values().forEach(Collections::sort);
    }

    @NotNull
    @Override
    public <E1 extends Event> E1 fire(@NonNull final E1 event) {
        val key = event.getClass();
        this.debug("Firing listeners for %s", key.getSimpleName());

        if (listeners.containsKey(key)) {
            val methods = new WeakReference<>(
                new ArrayList<>(listeners.get(key))); // todo: memory impact
            //noinspection ConstantConditions
            for (val method : methods.get()) {
                try {
                    method.invoke(event);
                }
                catch (final Exception e) {
                    this.debug("%s failed with an exception %s", method,
                        e.getClass().getSimpleName());
                    e.printStackTrace();
                }

                if (event.isCancelled()) {
                    this.debug("Event firing cancelled");
                    break;
                }
            }

            methods.clear();
        }
        else {
            this.debug("No listeners found");
        }

        return event;
    }

    private void debug(@NotNull final Object to_string, @Nullable final Object... format_args) {
        if (Properties.EVENT_DEBUG) {
            logger.debug(to_string, format_args);
        }
    }
}
