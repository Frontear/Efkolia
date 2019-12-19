package com.github.frontear.efkolia.loader;

import com.github.frontear.efkolia.mod.IMinecraftMod;

/**
 * The common interface that defines the java file that loads the entire mod and its functionality
 * Treat it like it is your main class, the entry-point for the entire program.
 */
public interface ILoaderMod {
    /**
     * The initialization of the mod. This is where the mod should begin to load it's functionality.
     * It should be treated like the main method of a class, an entry-point for your mod. This
     * should ideally be setting up your {@link IMinecraftMod}, and nothing more.
     */
    void init();
}
