package com.github.frontear.efkolia;

import com.github.frontear.efkolia.impl.configuration.Config;
import com.github.frontear.efkolia.impl.events.EventExecutor;
import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import lombok.NonNull;

/**
 * A utility class for the properties efkolia supports. All properties must start with "-Defkolia".
 */
public final class Properties {
    /**
     * The debugging property. Useful for when you need certain aspects of the client to be exposed
     * in a more thorough manner. For more specialized/isolated debugging, please see the remaining
     * debug flags. This will enable <b>all</b> of the other debugging flags, so exercise caution
     * when using it.
     */
    public static final boolean DEBUG = get("debug");

    /**
     * The configuration debugging property. This enables extremely verbose logging of {@link
     * Config}.
     */
    public static final boolean CONFIG_DEBUG = DEBUG || get("config.debug");

    /**
     * The events debugging property. This enables extremely verbose logging of {@link
     * EventExecutor}.
     */
    public static final boolean EVENT_DEBUG = DEBUG || get("event.debug");

    /**
     * The secure random property. This triggers the more secure functionality of {@link
     * LocalRandom} This property should be set via "-Defkolia.secure.random=true/false".
     */
    public static final boolean SECURE_RANDOM = get("secure.random");

    private static boolean get(@NonNull final String prop) {
        return Boolean.getBoolean("efkolia." + prop);
    }
}
