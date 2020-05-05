package com.github.frontear.efkolia.utilities.inspect.resolver;

import com.github.frontear.internal.NotNull;
import lombok.NonNull;

abstract class Descriptor {
    protected final String descriptor;
    private final String name;

    protected Descriptor(@NonNull final String name, @NonNull final String descriptor) {
        this.descriptor = descriptor;
        this.name = name;
    }

    @NotNull
    public String getDescriptor() {
        return descriptor;
    }

    @NotNull
    public String getName() {
        return name;
    }
}
