package com.github.frontear.efkolia.api.mod;

import com.github.frontear.efkolia.api.configuration.IConfig;
import com.github.frontear.efkolia.api.events.*;
import com.github.frontear.efkolia.api.info.IMetadata;
import com.github.frontear.efkolia.api.logging.ILogger;
import com.github.frontear.internal.NotNull;

/**
 * The interface which defines a minecraft mod. This should ideally contain all of your objects, and
 * pass around safe references to the object. It is your global container, which all the
 * functionality of your mod depends upon and derives from.
 */
public interface IMinecraftMod {
    /**
     * The metadata of the mod. This contains all the information that would be necessary to display
     * to the user.
     *
     * @return An instance of {@link IMetadata}.
     */
    @NotNull
    IMetadata getMetadata();

    /**
     * The logging system of the mod. This creates a "child" of a previously configured {@link
     * ILogger}, and will provide the user with it. This does NOT return the original instance,
     * rather it uses {@link ILogger#child(String)}.
     *
     * @param name The name of the child logger.
     *
     * @return A logger that is semantically linked to a parent.
     */
    @NotNull
    ILogger getLogger(@NotNull final String name);

    /**
     * The event registration and execution system for the mod. This allows you to easily and
     * effectively register and link certain objects to invoke {@link Listener} methods.
     *
     * @return An instance of {@link IEventExecutor}.
     */
    @NotNull
    IEventExecutor<?> getExecutor();

    /**
     * The config of the mod. This will synchronize and manage all objects that are configured
     * through an external file.
     *
     * @return An instance of {@link IConfig}.
     */
    @NotNull
    IConfig getConfig();
}
