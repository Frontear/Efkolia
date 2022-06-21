package com.github.frontear.efkolia.impl.metadata;

import com.github.frontear.efkolia.api.metadata.IMetadata;
import com.github.frontear.internal.Nonnull;
import com.github.frontear.internal.Nullable;

public class Metadata implements IMetadata {
    private final String modid, name, version, description;
    private final String[] authors, contributors;
    private final String formatted_name, formatted_author_list, formatted_contributor_list;

    public Metadata(@Nonnull String modid, @Nonnull String name, @Nonnull String version, @Nullable String description, @Nonnull String[] authors, @Nullable String... contributors) {
        this.modid = modid;
        this.name = name;
        this.version = version;
        this.description = description;

        this.authors = authors;
        this.contributors = contributors;

        this.formatted_name = String.format("%s v%s", name, version);
        // todo: repetitive code, maybe move into some reusable function?
        {
            String joined = String.join(", ", authors);
            int index = joined.lastIndexOf(", ");
            this.formatted_author_list = index != -1 ? joined.substring(0, index) + ", and " + joined.substring(index + ", ".length()) : joined;
        }
        if (contributors != null) {
            String joined = String.join(", ", contributors);
            int index = joined.lastIndexOf(", ");
            this.formatted_contributor_list = index != -1 ? joined.substring(0, index) + ", and " + joined.substring(index + ", ".length()) : joined;
        } else {
            this.formatted_contributor_list = null;
        }
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

    @Nonnull
    public String getFormattedName() {
        return formatted_name;
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

    @Nonnull
    public String getFormattedAuthorList() {
        return formatted_author_list;
    }

    @Nullable
    @Override
    public String[] getContributors() {
        return contributors;
    }

    @Nullable
    public String getFormattedContributorList() {
        return formatted_contributor_list;
    }
}
