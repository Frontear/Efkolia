package com.github.frontear.efkolia.api.configuration;

import com.github.frontear.internal.NotNull;

/**
 * The interface which defines a config service. This config service must be able to load
 * information from files and parse them into objects, specifically {@link IConfigurable}. It must
 * register the objects that require modification, and modify them whenever the config file loads.
 */
public interface IConfig {
    /**
     * Registers an object for being configured. This object will have its internal state modified
     * anytime {@link IConfig#load()} is run.
     *
     * @param object The object to register.
     */
    void register(@NotNull final IConfigurable<?> object);

    /**
     * Removes the registration of an object from being configured. The object will not have its
     * internal state changed even when {@link IConfig#load()} is run.
     *
     * @param object The object to unregister.
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
