package com.github.frontear.efkolia.utilities.inspect.resolver;

import com.github.frontear.internal.NotNull;
import lombok.NonNull;

/**
 * An abstract level descriptor which handles the containment of a JVM descriptor alongside it's
 * original identity for frontend usage.
 */
abstract class Descriptor {
    protected final String descriptor;
    private final String name;

    /**
     * @param name       The frontend naming for the JVM member.
     * @param descriptor The descriptor for the JVM member.
     */
    protected Descriptor(@NonNull final String name, @NonNull final String descriptor) {
        this.descriptor = descriptor;
        this.name = name;
    }

    /**
     * @return The JVM member's descriptor.
     */
    @NotNull
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * @return The JVM member's frontend name.
     */
    @NotNull
    public String getName() {
        return name;
    }
}
