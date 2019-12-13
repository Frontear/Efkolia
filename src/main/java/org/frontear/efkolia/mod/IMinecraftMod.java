package org.frontear.efkolia.mod;

import org.frontear.efkolia.logging.ILogger;

/**
 * The interface which defines a minecraft mod. This should ideally contain all of your objects, and
 * pass around safe references to the object. It is your global container, which all the
 * functionality of your mod depends and derives from the data managed here
 */
public interface IMinecraftMod {
    /**
     * The logging system of the mod. All logging pertaining to the mod and its functionality should
     * go through here, and NOT through individual log instances
     *
     * @return An instance of {@link ILogger}
     */
    ILogger getLogger();
}
