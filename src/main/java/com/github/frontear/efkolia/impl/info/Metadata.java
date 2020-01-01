package com.github.frontear.efkolia.impl.info;

import com.github.frontear.efkolia.api.info.IMetadata;
import com.github.frontear.internal.*;
import lombok.val;

public final class Metadata implements IMetadata {
    private final String name, version, authors;

    public Metadata(@NotNull final String name, @NotNull final String version,
        @NotNull final String author, @Nullable final String... contributors) {
        this.name = name;
        this.version = version;

        if (contributors == null) {
            this.authors = author;
        }
        else {
            val authors = new String[contributors.length + 1]; // + 1 for the 'author'
            authors[0] = author;
            System.arraycopy(contributors, 0, authors, 1, contributors.length);

            val joined = String.join(", ", authors);
            val index = joined.lastIndexOf(", ");
            this.authors = index != -1 ? joined.substring(0, index) + ", and " + joined
                .substring(index + ", ".length()) : joined;
        }
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
