package com.github.frontear.efkolia.api.loader;

import com.github.frontear.efkolia.api.mod.IMinecraftMod;
import com.github.frontear.internal.*;

/**
 * The common interface that defines the java file that loads the entire mod and its functionality
 * Treat it like it is your main class, the entry-point for the entire program.
 *
 * @param <T> The type you wish to return after instantiation.
 */
public interface ILoaderMod<T> {
    /**
     * The initialization of the mod. This is where the mod should begin loading its functionality.
     * It should be treated like the main method of a class, an entry-point for your mod. This
     * should ideally be setting up your {@link IMinecraftMod}, and nothing more.
     *
     * @param args The starting arguments for the application.
     *
     * @return A type which will represent whether successful initialization occurred.
     */
    @NotNull
    T init(@Nullable String... args);
}
