package com.github.frontear.efkolia.impl.metadata;

import com.github.frontear.efkolia.api.metadata.IMetadata;
import com.github.frontear.internal.Nonnull;
import com.github.frontear.internal.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * A simple implementation of the {@link IMetadata} interface. Data values are guaranteed to be non-null (when
 * applicable), however they are <b>not</b> guaranteed to be conforming. For a conforming, validated Metadata class, use
 * {@link StrictMetadata}.
 */
public class SimpleMetadata implements IMetadata {
    private final String modid, name, version, description;
    private final String[] authors, contributors;

    public SimpleMetadata(@Nonnull String modid, @Nonnull String name, @Nonnull String version, @Nullable String description, @Nonnull String[] authors, @Nullable String... contributors) {
        this.modid = Objects.requireNonNull(modid, "modid is null");
        this.name = Objects.requireNonNull(name, "name is null");
        this.version = Objects.requireNonNull(version, "version is null");
        this.description = description;

        // deep copy to prevent outside manipulation
        this.authors = Arrays.copyOf(Objects.requireNonNull(authors, "authors is null"), authors.length);
        this.contributors = contributors != null ? Arrays.copyOf(contributors, contributors.length) : null;
    }

    @Nonnull
    @Override
    public String getModId() {
        return modid;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public String getVersion() {
        return version;
    }

    @Nullable
    @Override
    public String getDescription() {
        return description;
    }

    @Nonnull
    @Override
    public String[] getAuthors() {
        return authors;
    }

    @Nullable
    @Override
    public String[] getContributors() {
        return contributors;
    }
}
