package com.github.frontear.efkolia.utilities.inspect.resolvers;

import com.github.frontear.efkolia.utilities.inspect.MappingResolver;
import com.github.frontear.internal.NotNull;
import lombok.NonNull;

public final class DummyResolver implements MappingResolver {
    @NotNull
    @Override
    public String resolveClass(@NonNull final String pkg, @NonNull final String name) {
        return pkg + "." + name;
    }

    @NotNull
    @Override
    public String resolveMethod(@NonNull final String name) {
        return name;
    }

    @NotNull
    @Override
    public String resolveField(@NonNull final String name) {
        return name;
    }
}
