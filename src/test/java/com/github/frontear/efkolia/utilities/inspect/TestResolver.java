package com.github.frontear.efkolia.utilities.inspect;

import com.github.frontear.efkolia.utilities.inspect.exceptions.NoSuchMappingException;
import com.github.frontear.efkolia.utilities.inspect.resolver.*;

class TestResolver implements MappingResolver {
    @Override
    public TypeDescriptor resolveClass(final String pkg, final String name)
        throws NoSuchMappingException {
        return new TypeDescriptor(pkg, name);
    }

    @Override
    public String resolveMethod(final MethodDescriptor descriptor) throws NoSuchMappingException {
        return descriptor.getName();
    }

    @Override
    public String resolveField(final FieldDescriptor descriptor) throws NoSuchMappingException {
        return descriptor.getName();
    }
}
