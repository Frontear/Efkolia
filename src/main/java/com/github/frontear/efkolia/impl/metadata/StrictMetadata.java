package com.github.frontear.efkolia.impl.metadata;

import com.github.frontear.efkolia.api.metadata.IMetadata;
import com.github.frontear.internal.Nonnull;
import com.github.frontear.internal.Nullable;

import java.security.InvalidParameterException;

/**
 * Metadata class which conforms to the specification and criterion as specified in the {@link IMetadata} interface.
 * Data values are guaranteed to be non-null (when applicable).
 * <p>
 * <ul>
 *     <li>modid is guaranteed to be all lowercase, separated by underscores, and alphanumeric.</li>
 *     <li>name is guaranteed to be matching the modid, with Pascal Casing, all underscores replaced with spaces.</li>
 *     <li>version is guaranteed to be conforming to semantic version 2.0.0 (major.minor.patch).</li>
 *     <li>description is guaranteed to not be blank, but only if it is not null.</li>
 *     <li>authors are guaranteed to not contain any null authors or empty names.</li>
 *     <li>contributors are guaranteed to not contain any null contributors or empty names.</li>
 * </ul>
 * <p>
 * For a more comprehensive list, please see {@link IMetadata}.
 */
public class StrictMetadata implements IMetadata {
    private final String modid, name, version, description;
    private final String[] authors, contributors;

    @SuppressWarnings("ConstantConditions")
    public StrictMetadata(@Nonnull String modid, @Nonnull String name, @Nonnull String version, @Nullable String description, @Nonnull String[] authors, @Nullable String... contributors) {
        {
            if (modid == null) {
                throw new NullPointerException("modid is null");
            }
            for (int i = 0; i < modid.length(); ++i) {
                char c = modid.charAt(i);

                if (Character.isUpperCase(c)) {
                    throw new InvalidParameterException("modid contains an uppercase (contains '%c')".formatted(c));
                }

                if (Character.isSpaceChar(c)) {
                    throw new InvalidParameterException("modid contains a space character (contains '%c')".formatted(c));
                }

                if (!Character.isAlphabetic(c) && !Character.isDigit(c) && c != '_') {
                    throw new InvalidParameterException("modid contains non alpha-numeric or special characters (contains '%c')".formatted(c));
                }
            }

            this.modid = modid;
        }
        {
            if (name == null) {
                throw new NullPointerException("name is null");
            }
            // performs conversion from a modid to a name
            String[] split = modid.split("_");
            for (int i = 0; i < split.length; ++i) {
                split[i] = Character.toUpperCase(split[i].charAt(0)) + split[i].substring(1);
            }

            String conforming_name = String.join(" ", split);

            if (!name.equals(conforming_name)) {
                throw new InvalidParameterException("name does not conform to the modid value (expected '%s', got '%s')".formatted(conforming_name, name));
            }

            this.name = name;
        }
        {
            if (version == null) {
                throw new NullPointerException("version is null");
            }

            // https://ihateregex.io/expr/semver/
            if (!version.matches("^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$")) {
                throw new InvalidParameterException("version does not conform to semver 2.0 (expected major.minor.patch, got '%s')".formatted(version));
            }

            this.version = version;
        }
        {
            if (description != null && description.isBlank()) {
                throw new InvalidParameterException("a non-null description should not be blank");
            }

            this.description = description;
        }
        {
            if (authors == null) {
                throw new NullPointerException("authors is null");
            }
            for (int i = 0; i < authors.length; ++i) {
                if (authors[i] == null) {
                    throw new NullPointerException("an author is null (at index '%d')".formatted(i));
                }

                if (authors[i].isBlank()) {
                    throw new InvalidParameterException("an author is blank (at index '%d')".formatted(i));
                }
            }

            this.authors = authors;
        }
        {
            if (contributors != null) {
                for (int i = 0; i < contributors.length; ++i) {
                    if (contributors[i] == null) {
                        throw new NullPointerException("a contributor is null (at index '%d')".formatted(i));
                    }

                    if (contributors[i].isBlank()) {
                        throw new InvalidParameterException("a contributor is blank (at index '%d')".formatted(i));
                    }
                }
            }

            this.contributors = contributors;
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
