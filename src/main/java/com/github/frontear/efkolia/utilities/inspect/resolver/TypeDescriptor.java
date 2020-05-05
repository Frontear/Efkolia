package com.github.frontear.efkolia.utilities.inspect.resolver;

import lombok.NonNull;

public final class TypeDescriptor extends Descriptor {
    public static final TypeDescriptor BYTE = new TypeDescriptor("B");
    public static final TypeDescriptor SHORT = new TypeDescriptor("S");
    public static final TypeDescriptor INT = new TypeDescriptor("I");
    public static final TypeDescriptor LONG = new TypeDescriptor("J");
    public static final TypeDescriptor FLOAT = new TypeDescriptor("F");
    public static final TypeDescriptor DOUBLE = new TypeDescriptor("D");
    public static final TypeDescriptor BOOLEAN = new TypeDescriptor("Z");
    public static final TypeDescriptor CHAR = new TypeDescriptor("C");

    private TypeDescriptor(@NonNull final String descriptor) {
        super("", descriptor);
    }

    public TypeDescriptor(@NonNull final String pkg, @NonNull final String name) {
        super(pkg + "." + name, "L" + pkg.replace(".", "/") + name + ";");
    }

    public TypeDescriptor(@NonNull final Class<?> type) {
        this(type.getPackage().getName(), type.getSimpleName());
    }
}
