package com.github.frontear.efkolia;

import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import lombok.NonNull;

/**
 * A utility class for the properties efkolia supports. All properties must start with "-Defkolia".
 */
public final class Properties {
    /**
     * The debugging property. Useful for when you need certain aspects of the client to be exposed
     * in a more thorough manner. This property should be set via "-Defkolia.debug=true/false".
     */
    public static final boolean DEBUG = get("debug");

    /**
     * The secure random property. This triggers the more secure functionality of {@link
     * LocalRandom} This property should be set via "-Defkolia.secure.random=true/false".
     */
    public static final boolean SECURE_RANDOM = get("secure.random");

    private static boolean get(@NonNull final String prop) {
        return Boolean.getBoolean("efkolia." + prop);
    }
}
