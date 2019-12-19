package org.frontear.efkolia.configuration;

import org.frontear.internal.NotNull;

/**
 * The interface which defines a config service. This config service must be able to load
 * information from files and parse them into objects, specifically {@link IConfigurable}. It must
 * register the objects that require modification, and modify them whenever the config file is
 * loaded.
 */
public interface IConfig {
    /**
     * Registers an object for configuration. This object will have its internal state modified
     * anytime {@link IConfig#load()} is run.
     *
     * @param object The object to register to the configuration
     */
    void register(@NotNull final IConfigurable<?> object);

    /**
     * Removes the registration of an object from being configured. The object will no longer be
     * modified.
     *
     * @param object The object to register to the configuration
     */
    void unregister(@NotNull final IConfigurable<?> object);

    /**
     * Attempts to read the configuration file into the program, then iterate on all {@link
     * IConfig#register(IConfigurable)} elements to modify their internal state.
     */
    void load();

    /**
     * Saves the internal state of all {@link IConfig#register(IConfigurable)} elements to the
     * configuration file.
     */
    void save();
}
