package org.frontear.efkolia.metadata;

import org.frontear.internal.*;

/**
 * This interface is designated to contain all the metadata information about a mod. Any metadata
 * marked with {@link Nullable} is not mandatory, and can be safely skipped/not contained. Anything
 * else MUST be a part of the metadata, and accessible through their respective getters
 */
public interface IMetadataMod {
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
     * @return The description about the mod's functionality
     */
    @Nullable
    String getDescription();

    /**
     * @return The authors responsible for developing the mod
     */
    @NotNull
    String[] getAuthors();
}
