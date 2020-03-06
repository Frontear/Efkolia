package com.github.frontear.efkolia.utilities.inspect.resolvers;

import com.github.frontear.efkolia.utilities.inspect.MappingResolver;
import com.github.frontear.efkolia.utilities.inspect.exceptions.NoSuchMappingException;
import com.github.frontear.internal.NotNull;
import lombok.NonNull;

public final class DummyResolver implements MappingResolver {
    @NotNull
    @Override
    public String resolveClass(@NonNull final String pkg, @NonNull final String name)
        throws NoSuchMappingException {
        return pkg + "." + name;
    }

    @NotNull
    @Override
    public String resolveMethod(@NonNull final String clazz, @NonNull final String name,
        @NonNull final String descriptor)
        throws NoSuchMappingException {
        return name;
    }

    @NotNull
    @Override
    public String resolveField(@NonNull final String clazz, @NonNull final String name,
        @NonNull final String descriptor)
        throws NoSuchMappingException {
        return name;
    }
}
