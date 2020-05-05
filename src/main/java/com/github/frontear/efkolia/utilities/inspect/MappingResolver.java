package com.github.frontear.efkolia.utilities.inspect;

import com.github.frontear.efkolia.utilities.inspect.exceptions.NoSuchMappingException;
import com.github.frontear.internal.NotNull;

/**
 * An interface which allows the resolving of mappings in an obfuscated Java environment. This
 * handles cases where you may be working in a obfuscation-free development environment, but will
 * have obfuscation in production. This requires you to handle the resolution of various members
 * through your own methodology. Furthermore, this requires custom logic to determine whether the
 * current environment has obfuscated fields (dev vs prod).
 */
// todo: make simpler for the end-user (ie remove descriptors)
public interface MappingResolver {
    /**
     * Resolves an obfuscated package/class combination. It should correctly format and return the
     * obfuscated name.
     * <p>
     * e.g: - Development: com.abc.Main - Production: c.a.M
     * <p>
     * In this situation, {@link #resolveClass(String, String)} should return "c.a.M".
     *
     * @param pkg  The package where the class resides in.
     * @param name The name of the class.
     *
     * @return The package + class name in its obfuscated form.
     *
     * @throws NoSuchMappingException if the specified mapping could not be found.
     */
    @NotNull
    String resolveClass(@NotNull final String pkg, @NotNull final String name)
        throws NoSuchMappingException;

    /**
     * Resolves an obfuscated method. It should correctly return the obfuscated name
     * <p>
     * e.g: - Development: format() - Production: f()
     * <p>
     * In this situation, {@link #resolveMethod(String, String, String)} should return "f".
     *
     * @param clazz      The obfuscated name of the class, received from {@link
     *                   #resolveClass(String, String)}.
     * @param name       The name of the method.
     * @param descriptor The descriptor of the method, such as ()V for a void, no parameter method.
     *
     * @return The method name in its obfuscated form.
     *
     * @throws NoSuchMappingException if the specified mapping could not be found.
     */
    @NotNull
    String resolveMethod(@NotNull final String clazz, @NotNull final String name,
        @NotNull final String descriptor) throws NoSuchMappingException;

    /**
     * Resolves an obfuscated field. It should correctly return the obfuscated name
     * <p>
     * e.g: - Development: number - Production: n
     * <p>
     * In this situation, {@link #resolveField(String, String, String)} should return "n".
     *
     * @param clazz      The obfuscated name of the class, received from {@link
     *                   #resolveClass(String, String)}.
     * @param name       The name of the field.
     * @param descriptor The descriptor of the method, such as I for a int field.
     *
     * @return The field name in its obfuscated form.
     *
     * @throws NoSuchMappingException if the specified mapping could not be found.
     */
    @NotNull
    String resolveField(@NotNull final String clazz, @NotNull final String name,
        @NotNull final String descriptor) throws NoSuchMappingException;
}
