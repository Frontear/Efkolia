package com.github.frontear.efkolia.api.metadata;

import com.github.frontear.internal.Nonnull;
import com.github.frontear.internal.Nullable;

/**
 * Metadata which pertains to a specific Minecraft mod. Information can be sourced from a config file or hard-coded at
 * the discretion of the developer. All data must be present at runtime, that is to say, none of the data (with certain
 * exceptions) should be null at any stage of execution.
 * <p>
 * The only exceptions for data to be null or not present, is if the existence of said data depends on extraneous
 * circumstances that the mod has not yet qualified for. In such a case the mod, being unable to qualify, is not
 * required to present said information.
 * <p>
 * DATA CORRECTNESS IS NOT GUARANTEED. Standards defined here are subject to the mod developer, and are not required to
 * be enforced in any implementation.
 */
public interface IMetadata {
    /**
     * The mod identifier, used mostly for internal mechanisms by most mod loaders. A mod id tends to be all lowercase
     * and no spaces, and conventionally registers for mod loaders as the name of the mod. Any mods with matching ids
     * can cause errors as they are treated as the exact same mod, despite not necessarily being so, henceforth it is
     * important for this to be unique.
     * <p>
     * The identifier should be kept relatively short, have no spaces, and be comprised of only letters and numbers. No
     * symbols (except the underscore) should be present.
     *
     * @return The unique identifier for the mod.
     */
    @Nonnull
    String getModId();

    /**
     * The front-end user-friendly name for the mod. This does not have to match the internal mod id in any capacity,
     * and is simply the mod name displayed to the end-user, and the name the user would know the mod as.
     * <p>
     * This name, for the purposes of sanity and organization should be kept similar to, if not the exact same, as the
     * mod id. This is to ensure consistent communication across development internally and keeps naming relatively
     * non-confusing.
     *
     * @return The user-friendly name for the mod.
     */
    @Nonnull
    String getName();

    /**
     * The version of the mod, conventionally written in the <a href="https://semver.org/">semantic versioning 2.0.0</a>
     * specification. Versioning is useful to indicate the age and development progress of the mod.
     * <p>
     * Although not mandatory to follow convention, it is recommended heavily to ensure a consistent standard and
     * clarity amongst developers and modders alike.
     *
     * @return The current version of the mod.
     */
    @Nonnull
    String getVersion();

    /**
     * The mod description tends to contain one to two sentences of information in regard to mod capabilities and
     * functionality. This information exists primarily for the end-user, providing insight as to what the mod can do.
     * <p>
     * This information is not mandatory, though heavily recommended. If present, descriptions should generally be
     * concise, and provide a rough explanation of the mod. Using simple and non-technical terminology is usually better
     * for users, and should be followed unless the mod fundamentally exists for technical users.
     *
     * @return A simple, non-technical description for the mod.
     */
    @Nullable
    String getDescription();

    /**
     * A list of mod authors. Authors are considered part of the core development team, the original creators and
     * executives responsible for bringing the mod to life.
     * <p>
     * Names can be written as chosen by the developers, either their username, real name, or a combination of the two.
     * The authors list should be unique and contain at least one author.
     *
     * @return The authors in charge of the development for the mod.
     */
    @Nonnull
    String[] getAuthors();

    /**
     * Contributors are developers that have modified or updated the source code of the mod, directly or indirectly.
     * <p>
     * This information is not mandatory, especially if there are no contributors. If present, names should be the
     * usernames or online names of the developer that contributed to the mod. This is to identify the developer easily
     * and trace their contributions back.
     *
     * @return The contributors that have contributed at least once to the mod.
     */
    @Nullable
    String[] getContributors();
}
