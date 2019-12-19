package org.frontear.impl.info;

import lombok.val;
import org.frontear.efkolia.info.IMetadata;
import org.frontear.internal.*;

public final class Metadata implements IMetadata {
    private final String name, version, description, authors;

    public Metadata(@NotNull final String name, @NotNull final String version,
        @Nullable final String description, @NotNull final String... authors) {
        this.name = name;
        this.version = version;
        this.description = description;

        val joined = String.join(", ", authors);
        val index = joined.lastIndexOf(", ");
        this.authors = index != -1 ? joined.substring(0, index) + ", and " + joined
            .substring(index + ", ".length()) : joined;
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public String getVersion() {
        return version;
    }

    @Nullable
    @Override
    public String getDescription() {
        return description;
    }

    @NotNull
    @Override
    public String getAuthors() {
        return authors;
    }
}
