package org.frontear.impl.info;

import lombok.val;
import org.frontear.efkolia.info.IMetadata;
import org.frontear.internal.NotNull;

public final class Metadata implements IMetadata {
    private final String name, version, authors;

    public Metadata(@NotNull final String name, @NotNull final String version,
        @NotNull final String... authors) {
        this.name = name;
        this.version = version;

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

    @NotNull
    @Override
    public String getAuthors() {
        return authors;
    }
}
