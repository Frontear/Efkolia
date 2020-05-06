package com.github.frontear.efkolia.utilities.inspect.resolver;

import com.github.frontear.internal.NotNull;
import lombok.NonNull;

public final class FieldDescriptor extends Descriptor {
    private final TypeDescriptor clazz, type;

    public FieldDescriptor(@NonNull final TypeDescriptor clazz, @NonNull final String name,
        @NonNull final TypeDescriptor type) {
        super(name, clazz.descriptor + name + ":" + type.descriptor);

        this.clazz = clazz;
        this.type = type;
    }

    @NotNull
    public TypeDescriptor getContainingClass() {
        return clazz;
    }

    @NotNull
    public TypeDescriptor getReturnType() {
        return type;
    }
}
