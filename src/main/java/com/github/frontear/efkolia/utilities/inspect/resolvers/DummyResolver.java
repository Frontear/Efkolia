package com.github.frontear.efkolia.utilities.inspect.resolvers;

import com.github.frontear.efkolia.utilities.inspect.MappingResolver;

public final class DummyResolver implements MappingResolver {
    @Override
    public String resolveClass(final String pkg, final String name) {
        return pkg + "." + name;
    }

    @Override
    public String resolveMethod(final String name) {
        return name;
    }

    @Override
    public String resolveField(final String name) {
        return name;
    }
}
