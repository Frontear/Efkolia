package com.github.frontear.efkolia.utilities.inspect;

import com.github.frontear.efkolia.utilities.inspect.exceptions.NoSuchMappingException;
import com.github.frontear.efkolia.utilities.inspect.resolver.*;
import com.github.frontear.internal.NotNull;

/**
 * An interface which allows the resolving of mappings in an obfuscated Java environment. This
 * handles cases where you may be working in a obfuscation-free development environment, but will
 * have obfuscation in production. This requires you to handle the resolution of various members
 * through your own methodology. Furthermore, this requires custom logic to determine whether the
 * current environment has obfuscated fields (dev vs prod).
 */
public interface MappingResolver {
    /**
     * Resolves an obfuscated package/class combination. It should correctly format and return the
     * obfuscated name.
     * <p>
     * e.g: - Development: com.abc.Main - Production: c.a.M
     * <p>
     * In this situation, {@link #resolveClass(String, String)} should return an obfuscated entry of
     * {@link TypeDescriptor}.
     *
     * @param pkg  The package where the class resides in.
     * @param name The name of the class.
     *
     * @return The package + class name in its obfuscated form.
     *
     * @throws NoSuchMappingException if the specified mapping could not be found.
     */
    @NotNull
    TypeDescriptor resolveClass(@NotNull final String pkg, @NotNull final String name)
        throws NoSuchMappingException;

    /**
     * Resolves an obfuscated method. It should correctly return the obfuscated name
     * <p>
     * e.g: - Development: format() - Production: f()
     * <p>
     * In this situation, {@link #resolveMethod(MethodDescriptor)} should return "f".
     *
     * @param descriptor The full descriptor for the method.
     *
     * @return The method name in its obfuscated form.
     *
     * @throws NoSuchMappingException if the specified mapping could not be found.
     */
    @NotNull
    String resolveMethod(@NotNull final MethodDescriptor descriptor) throws NoSuchMappingException;

    /**
     * Resolves an obfuscated field. It should correctly return the obfuscated name
     * <p>
     * e.g: - Development: number - Production: n
     * <p>
     * In this situation, {@link #resolveField(FieldDescriptor)} should return "n".
     *
     * @param descriptor The full descriptor for the field.
     *
     * @return The field name in its obfuscated form.
     *
     * @throws NoSuchMappingException if the specified mapping could not be found.
     */
    @NotNull
    String resolveField(@NotNull final FieldDescriptor descriptor) throws NoSuchMappingException;
}
