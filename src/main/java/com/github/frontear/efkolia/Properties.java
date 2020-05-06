package com.github.frontear.efkolia;

import com.github.frontear.efkolia.impl.configuration.Config;
import com.github.frontear.efkolia.impl.container.Container;
import com.github.frontear.efkolia.impl.events.EventExecutor;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import lombok.NonNull;

/**
 * A utility class for the properties efkolia supports. All properties are prefixed with "efkolia",
 * properties are all lowercase and all cases of '_' are replaced with '.' in the JVM flags.
 */
public final class Properties {
    /**
     * The main debugging flag which will enable {@link Logger#debug(Object, Object...)}. This
     * automatically enables all other debugging flags.
     */
    public static final boolean DEBUG = get("debug");

    /**
     * Enables verbose logging and information from {@link Config}.
     */
    public static final boolean CONFIG_DEBUG = DEBUG || get("config.debug");

    /**
     * Enables verbose logging and information from {@link Container}.
     */
    public static final boolean CONTAINER_DEBUG = DEBUG || get("container.debug");

    /**
     * Enables verbose logging and information from {@link EventExecutor}.
     */
    public static final boolean EVENT_DEBUG = DEBUG || get("event.debug");

    /**
     * Enables a legacy functionality in {@link Logger} that forces a prefix to each log entry.
     */
    public static final boolean LEGACY_LOGGER = get("legacy.logger");

    /**
     * Enables the secure randomization functionality of {@link LocalRandom}.
     */
    public static final boolean SECURE_RANDOM = get("secure.random");

    private static boolean get(@NonNull final String prop) {
        return Boolean.getBoolean("efkolia." + prop);
    }
}
