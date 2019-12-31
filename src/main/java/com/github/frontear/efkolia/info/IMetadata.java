package com.github.frontear.efkolia.info;

import com.github.frontear.internal.NotNull;

/**
 * This interface is designated to contain all the metadata information about a mod. None of the
 * information should be null, and all of it should be valid and easily accessible.
 */
public interface IMetadata {
    /**
     * @return The name of the mod
     */
    @NotNull
    String getName();

    /**
     * @return The version of the mod
     */
    @NotNull
    String getVersion();

    /**
     * @return The authors responsible for developing the mod
     */
    @NotNull
    String getAuthors();
}
