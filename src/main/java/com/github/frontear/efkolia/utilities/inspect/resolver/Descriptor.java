package com.github.frontear.efkolia.utilities.inspect.resolver;

import lombok.*;

abstract class Descriptor {
    @Getter protected final String descriptor;
    @Getter private final String name;

    protected Descriptor(@NonNull final String name, @NonNull final String descriptor) {
        this.descriptor = descriptor;
        this.name = name;
    }
}
