package org.frontear.efkolia.mod;

import org.frontear.efkolia.configuration.IConfig;
import org.frontear.efkolia.logging.ILogger;
import org.frontear.efkolia.metadata.IMetadataMod;
import org.frontear.internal.*;

/**
 * The interface which defines a minecraft mod. This should ideally contain all of your objects, and
 * pass around safe references to the object. It is your global container, which all the
 * functionality of your mod depends and derives from the data managed here.
 */
public interface IMinecraftMod {
    /**
     * The metadata of the mod. This should be carefully considered, as not necessarily all the
     * information that it claims to provide actually exists, as some may be marked {@link
     * Nullable}
     *
     * @return An instance of {@link IMetadataMod}
     */
    @NotNull
    IMetadataMod getMetadata();

    /**
     * The config of the mod. This will synchronize and manage all objects that are configured
     * through an external file
     *
     * @param <E> The config type that each configurable conforms to
     *
     * @return An instance of {@link IConfig}
     */
    @NotNull
    <E> IConfig<E> getConfig();

    /**
     * The logging system of the mod. All logging pertaining to the mod and its functionality should
     * go through here, and NOT through individual log instances.
     *
     * @return An instance of {@link ILogger}
     */
    @NotNull
    ILogger getLogger();
}
