package com.github.frontear.efkolia.utilities.inspect.resolver;

import lombok.NonNull;

public final class FieldDescriptor extends Descriptor {
    public FieldDescriptor(@NonNull final TypeDescriptor clazz, @NonNull final String name,
        @NonNull final TypeDescriptor type) {
        super(name, clazz.descriptor + name + ":" + type.descriptor);
    }
}
