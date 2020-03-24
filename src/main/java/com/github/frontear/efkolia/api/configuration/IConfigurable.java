package com.github.frontear.efkolia.api.configuration;

import com.github.frontear.internal.NotNull;

/**
 * The interface given to all items that are to be internally modified via {@link IConfig}.
 *
 * @param <E> The value {@link IConfig#load()} will give to your object. You then set the state
 *            through {@link #load(IConfigurable)}.
 */
public interface IConfigurable<E extends IConfigurable<E>> {
    /**
     * Loading information from the configuration file, and passing it to the {@link IConfigurable}
     * in order to allow it to extract the data it needs.
     *
     * @param value The information from the configuration file.
     */
    void load(@NotNull final E value);

    /**
     * This is used to identify what belongs to the object from the configuration file.
     *
     * @return The property name of the object.
     */
    @NotNull
    String getPropertyName();
}
