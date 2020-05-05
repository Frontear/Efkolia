package com.github.frontear.efkolia.utilities.inspect.resolver;

import com.github.frontear.internal.Nullable;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.NonNull;

public final class MethodDescriptor extends Descriptor {
    public MethodDescriptor(@NonNull final TypeDescriptor clazz, @NonNull final String name,
        @NonNull final TypeDescriptor type, @Nullable final TypeDescriptor... params) {
        super(name, clazz.descriptor + name + "(" + (params == null ? ""
            : Arrays.stream(params).map(x -> x.descriptor).collect(Collectors.joining())) + ")"
            + type.descriptor);
    }
}
