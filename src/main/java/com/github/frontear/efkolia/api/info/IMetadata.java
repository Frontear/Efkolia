package com.github.frontear.efkolia.api.info;

import com.github.frontear.internal.NotNull;

/**
 * This contains all the metadata information about a mod. None of the information should be null,
 * and all of it should be valid.
 */
public interface IMetadata {
    /**
     * @return The name of the mod.
     */
    @NotNull
    String getName();

    /**
     * @return The version of the mod.
     */
    @NotNull
    String getVersion();

    /**
     * @return The full name of the mod. This is simply the name + the version in the format: (%s v%s, name, version)
     */
    @NotNull
    default String getFullName() {
        return getName() + " v" + getVersion();
    }

    /**
     * @return The developers responsible for developing the mod. This includes the authors and any
     * contributors.
     */
    @NotNull
    String getDevelopers();
}
