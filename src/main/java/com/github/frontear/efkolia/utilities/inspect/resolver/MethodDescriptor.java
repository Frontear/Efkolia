package com.github.frontear.efkolia.utilities.inspect.resolver;

import com.github.frontear.internal.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.NonNull;

public final class MethodDescriptor extends Descriptor {
    private final TypeDescriptor clazz, type;
    private final TypeDescriptor[] params;

    public MethodDescriptor(@NonNull final TypeDescriptor clazz, @NonNull final String name,
        @NonNull final TypeDescriptor type, @Nullable final TypeDescriptor... params) {
        super(name, clazz.descriptor + name + "(" + (params == null ? ""
            : Arrays.stream(params).map(x -> x.descriptor).collect(Collectors.joining())) + ")"
            + type.descriptor);

        this.clazz = clazz;
        this.type = type;
        this.params = params;
    }

    @NotNull
    public TypeDescriptor getContainingClass() {
        return clazz;
    }

    @NotNull
    public TypeDescriptor getReturnType() {
        return type;
    }

    @NotNull
    public TypeDescriptor[] getParameterTypes() {
        return params;
    }
}
