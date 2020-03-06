package com.github.frontear.efkolia.utilities.inspect;

import com.github.frontear.efkolia.utilities.inspect.exceptions.NoSuchMappingException;
import com.github.frontear.internal.NotNull;
import java.lang.reflect.*;
import java.util.Arrays;
import lombok.*;
import lombok.experimental.UtilityClass;

/**
 * A simple reflection utility that allows for reflection in an obfuscated environment, where member
 * names differ from their source names.
 */
@UtilityClass
public class Reflector {
    /**
     * Search for a field within a specific type and its super classes. If the field is found, an
     * instance of {@link Field} is returned with no additional modifications.
     *
     * @param type       The type to search from initially, and to {@link Class#getSuperclass()}
     *                   later
     * @param name       The name of the field
     * @param descriptor The descriptor of the method, such as I for a int field
     * @param resolver   The mapping resolver for this specific environment
     *
     * @return An instance of {@link Field} if an applicable field is found
     *
     * @throws NoSuchFieldException If no field matching the name is found
     */
    @NotNull
    @SneakyThrows(NoSuchMappingException.class)
    public Field getField(@NonNull Class<?> type, @NonNull final String name,
        @NonNull final String descriptor, @NonNull final MappingResolver resolver)
        throws NoSuchFieldException {
        val copy = type; // kept for being able to log it via exception later

        do {
            val obf_name = resolver.resolveField(type.getName(), name, descriptor);
            val field = Arrays.stream(type.getDeclaredFields())
                .filter(x -> x.getName().equals(obf_name)).findFirst();
            if (field.isPresent()) {
                return field.get();
            }
        }
        while ((type = type.getSuperclass()) != null);

        throw new NoSuchFieldException(String
            .format("No field found with name %s in %s (or any superclasses)", name,
                copy.getSimpleName()));
    }

    /**
     * Search for a method within a specific type and its super classes. If the method is found, an
     * instance of {@link Method} is returned with no additional modifications.
     *
     * @param type       The type to search from initially, and to {@link Class#getSuperclass()}
     *                   later
     * @param name       The name of the method
     * @param descriptor The descriptor of the method, such as ()V for a void, no parameter method
     * @param resolver   The mapping resolver for this specific environment
     *
     * @return An instance of {@link Field} if an applicable method is found
     *
     * @throws NoSuchMethodException If no method matching the name is found
     */
    @NotNull
    @SneakyThrows(NoSuchMappingException.class)
    public Method getMethod(@NonNull Class<?> type, @NonNull String name,
        @NonNull final String descriptor, @NonNull final MappingResolver resolver)
        throws NoSuchMethodException {
        val copy = type; // kept for being able to log it via exception later

        do {
            val obf_name = resolver.resolveMethod(type.getName(), name, descriptor);
            val method = Arrays.stream(type.getDeclaredMethods())
                .filter(x -> x.getName().equals(obf_name)).findFirst();
            if (method.isPresent()) {
                return method.get();
            }
        }
        while ((type = type.getSuperclass()) != null);

        throw new NoSuchMethodException(String
            .format("No method found with name %s in %s (or any superclasses)", name,
                copy.getSimpleName()));
    }

    /**
     * Search for a class in the JVM. If the class is found, an instance of {@link Class} is
     * returned with no additional modifications.
     *
     * @param pkg      The package in which the class resides
     * @param name     The name of the class
     * @param resolver The mapping resolver for this specific environment
     *
     * @return An instance of {@link Class} if an applicable field is found
     *
     * @throws ClassNotFoundException If no class matching the signature is found
     */
    @NotNull
    @SneakyThrows(NoSuchMappingException.class)
    public Class<?> getClass(@NonNull final String pkg, @NonNull final String name,
        @NonNull final MappingResolver resolver) throws ClassNotFoundException {
        val obf_name = resolver.resolveClass(pkg, name);

        return Class.forName(obf_name);
    }
}
