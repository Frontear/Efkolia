package com.github.frontear.efkolia.impl.container;

import com.github.frontear.efkolia.Properties;
import com.github.frontear.efkolia.api.container.IContainer;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.github.frontear.internal.*;
import com.google.common.reflect.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;
import lombok.*;

public abstract class Container<T> implements IContainer<T> {
    protected final Logger logger;
    private final Map<Class<? extends T>, T> objects;

    @SneakyThrows({ IOException.class, ClassNotFoundException.class })
    @SuppressWarnings("UnstableApiUsage")
    public Container(@NonNull final MinecraftMod mod, @NonNull final String pkg,
        @Nullable final Object... args) {
        this.logger = mod.getLogger(getClass().getSimpleName());
        this.objects = new LinkedHashMap<>();
        {
            //noinspection unchecked
            val type = (Class<T>) new TypeToken<T>(getClass()) {
            }.getRawType(); // hacky trick that works only for generics that are explicitly mentioned in abstract classes.
            val loader = type.getClassLoader();
            this.debug("Found managed type: %s", type.getSimpleName());

            this.debug("Searching package: %s", pkg);
            for (val info : ClassPath.from(loader).getTopLevelClasses(pkg)) {
                val target = loader.loadClass(
                    info.getName()); // circumvent a weird VM bug where the class has a malformed identity

                if (type.isAssignableFrom(target)) {
                    val object = target.asSubclass(type);
                    this.debug("Found %s", object.getSimpleName());

                    try {
                        val constructor = object.getDeclaredConstructor(
                            Arrays.stream(args).map(Object::getClass).toArray(Class[]::new));
                        constructor.setAccessible(true);

                        objects.put(object, constructor.newInstance(args));
                    }
                    catch (final ReflectiveOperationException e) {
                        logger.warn("An error has occurred during instantiation of %s",
                            object.getSimpleName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @NotNull
    @Override
    public <T1 extends T> T1 get(@NonNull final Class<T1> target) {
        //noinspection unchecked
        return (T1) objects.get(target);
    }

    @NotNull
    @Override
    public Stream<T> stream() {
        return objects.values().stream();
    }

    private void debug(@NonNull final Object to_string, @Nullable final Object... format_args) {
        if (Properties.CONTAINER_DEBUG) {
            logger.debug(to_string, format_args);
        }
    }
}
