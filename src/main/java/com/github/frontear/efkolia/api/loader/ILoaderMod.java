package com.github.frontear.efkolia.api.loader;

import com.github.frontear.efkolia.api.mod.IMinecraftMod;
import com.github.frontear.internal.Nullable;

/**
 * The common interface that defines the java file that loads the entire mod and its functionality
 * Treat it like it is your main class, the entry-point for the entire program.
 */
public interface ILoaderMod {
    /**
     * The initialization of the mod. This is where the mod should begin loading its functionality.
     * It should be treated like the main method of a class, an entry-point for your mod. This
     * should ideally be setting up your {@link IMinecraftMod}, and nothing more.
     *
     * @param args The starting arguments for the application.
     */
    void init(@Nullable String... args);
}
