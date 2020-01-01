package com.github.frontear.efkolia.api.mod;

import com.github.frontear.efkolia.api.configuration.IConfig;
import com.github.frontear.efkolia.api.info.IMetadata;
import com.github.frontear.efkolia.api.logging.ILogger;
import com.github.frontear.internal.NotNull;

/**
 * The interface which defines a minecraft mod. This should ideally contain all of your objects, and
 * pass around safe references to the object. It is your global container, which all the
 * functionality of your mod depends and derives from the data managed here.
 */
public interface IMinecraftMod {
    /**
     * The metadata of the mod. This contains all of the information that would be necessary to
     * display to the user.
     *
     * @return An instance of {@link IMetadata}
     */
    @NotNull
    IMetadata getMetadata();

    /**
     * The config of the mod. This will synchronize and manage all objects that are configured
     * through an external file
     *
     * @return An instance of {@link IConfig}
     */
    @NotNull
    IConfig getConfig();

    /**
     * The logging system of the mod. All logging pertaining to the mod and its functionality should
     * go through here, and NOT through individual log instances.
     *
     * @return An instance of {@link ILogger}
     */
    @NotNull
    ILogger getLogger();
}
