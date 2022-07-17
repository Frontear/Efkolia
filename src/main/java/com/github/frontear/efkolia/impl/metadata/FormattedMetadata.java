package com.github.frontear.efkolia.impl.metadata;

import com.github.frontear.internal.Nonnull;
import com.github.frontear.internal.Nullable;

/**
 * Metadata class which extends {@link SimpleMetadata} to add extra fields that provide human-readible Strings for
 * certain data values.
 */
public class FormattedMetadata extends SimpleMetadata {
    private final String formatted_name, formatted_author_list, formatted_contributor_list;

    public FormattedMetadata(@Nonnull String modid, @Nonnull String name, @Nonnull String version, @Nullable String description, @Nonnull String[] authors, @Nullable String... contributors) {
        super(modid, name, version, description, authors, contributors);

        this.formatted_name = String.format("%s v%s", name, version);

        // todo: move to method?
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

    /**
     * The formatted name is a short hand to merge the mod's name and version in a human readable format. The current
     * format is {name} v{version}.
     *
     * @return The user-friendly formatted name.
     */
    @Nonnull
    public String getFormattedName() {
        return formatted_name;
    }

    /**
     * The author list is formatted into a single string of comma separated authors. The final author is modified and
     * appended with an 'and', in order to make the string grammatically correct. For a list of authors [ "Alice",
     * "Bob", "Carl" ], the formatted string will be "Alice, Bob, and Carl".
     *
     * @return The formatted author list.
     */
    @Nonnull
    public String getFormattedAuthorList() {
        return formatted_author_list;
    }

    /**
     * The contributor list is formatted into a single string of comma separated contributors. The final contributor is
     * modified and appended with an 'and', in order to make the string grammatically correct. For a list of
     * contributors [ "Alex", "Bones", "Crampf" ], the formatted string will be "Alex, Bones, and Crampf".
     *
     * @return The formatted contributor list.
     */
    @Nullable
    public String getFormattedContributorList() {
        return formatted_contributor_list;
    }
}
