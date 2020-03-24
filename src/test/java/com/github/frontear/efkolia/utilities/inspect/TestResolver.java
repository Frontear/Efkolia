package com.github.frontear.efkolia.utilities.inspect;

import com.github.frontear.efkolia.utilities.inspect.exceptions.NoSuchMappingException;
import com.github.frontear.internal.NotNull;
import lombok.NonNull;

class TestResolver implements MappingResolver {
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
