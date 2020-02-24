package com.github.frontear.efkolia.utilities.inspect;

import com.github.frontear.internal.NotNull;

/**
 * An interface which allows the resolving of mappings in an obfuscated Java environment. This
 * handles cases where you may be working in a obfuscation-free development environment, but will
 * have obfuscation in production. This requires you to handle the resolution of various members
 * through your own methodology.
 */
public interface MappingResolver {
    /**
     * Resolves an obfuscated package/class combination. It should correctly format and return the
     * obfuscated name.
     * <p>
     * e.g: - Development: com.abc.Main - Production: c.a.M
     * <p>
     * In this situation, {@link MappingResolver#resolveClass(String, String)} should take "com.abc"
     * and "Main", and return "c.a.M"
     *
     * @param pkg  The package where the class resides in
     * @param name The name of the class
     *
     * @return The package + class name in its obfuscated form
     */
    String resolveClass(@NotNull final String pkg, @NotNull final String name);

    /**
     * Resolves an obfuscated method. It should correctly return the obfuscated name
     * <p>
     * e.g: - Development: format() - Production: f()
     * <p>
     * In this situation, {@link MappingResolver#resolveMethod(String)} should take "format" and
     * return "f"
     *
     * @param name The name of the method
     *
     * @return The method name in its obfuscated form
     */
    String resolveMethod(@NotNull final String name);

    /**
     * Resolves an obfuscated field. It should correctly return the obfuscated name
     * <p>
     * e.g: - Development: number - Production: n
     * <p>
     * In this situation, {@link MappingResolver#resolveField(String)} should take "number" and
     * return "n"
     *
     * @param name The name of the field
     *
     * @return The field name in its obfuscated form
     */
    String resolveField(@NotNull final String name);
}
