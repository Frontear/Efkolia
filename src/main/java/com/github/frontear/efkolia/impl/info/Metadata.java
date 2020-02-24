package com.github.frontear.efkolia.impl.info;

import com.github.frontear.efkolia.api.info.IMetadata;
import com.github.frontear.internal.Nullable;
import lombok.*;

public final class Metadata implements IMetadata {
    private final String name, version, developers;

    public Metadata(@NonNull final String name, @NonNull final String version,
        @NonNull final String author, @Nullable final String... contributors) {
        this.name = name;
        this.version = version;

        if (contributors == null) {
            this.developers = author;
        }
        else {
            val authors = new String[contributors.length + 1]; // + 1 for the 'author'
            authors[0] = author;
            System.arraycopy(contributors, 0, authors, 1, contributors.length);

            val joined = String.join(", ", authors);
            val index = joined.lastIndexOf(", ");
            this.developers = index != -1 ? joined.substring(0, index) + ", and " + joined
                .substring(index + ", ".length()) : joined;
        }
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String getVersion() {
        return version;
    }

    @NonNull
    @Override
    public String getDevelopers() {
        return developers;
    }
}
