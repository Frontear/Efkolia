package com.github.frontear.efkolia;

import com.github.frontear.efkolia.impl.configuration.Config;
import com.github.frontear.efkolia.impl.container.Container;
import com.github.frontear.efkolia.impl.events.EventExecutor;
import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import lombok.NonNull;

/**
 * A utility class for the properties efkolia supports. All properties are prefixed with "efkolia",
 * properties are all lowercase and all cases of '_' are replaced with '.' in the JVM flags.
 */
public final class Properties {
    /**
     * The global debugging property. It will automatically enable all other debugging flags,
     * irrespective of whether they have been set or not.
     */
    public static final boolean DEBUG = get("debug");

    /**
     * The configuration debugging property. This enables extremely verbose logging of {@link
     * Config}.
     */
    public static final boolean CONFIG_DEBUG = DEBUG || get("config.debug");

    /**
     * The container debugging property. This enables extremely verbose logging of {@link
     * Container}
     */
    public static final boolean CONTAINER_DEBUG = DEBUG || get("container.debug");

    /**
     * The events debugging property. This enables extremely verbose logging of {@link
     * EventExecutor}.
     */
    public static final boolean EVENT_DEBUG = DEBUG || get("event.debug");

    /**
     * The secure random property. This triggers the more secure functionality of {@link
     * LocalRandom}.
     */
    public static final boolean SECURE_RANDOM = get("secure.random");

    private static boolean get(@NonNull final String prop) {
        return Boolean.getBoolean("efkolia." + prop);
    }
}
