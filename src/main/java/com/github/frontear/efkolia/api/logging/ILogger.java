package com.github.frontear.efkolia.api.logging;

import com.github.frontear.internal.*;

/**
 * The common interface for an implementation of logging. The logger should be able to ideally run
 * {@link ILogger#info(Object, Object...)}, {@link ILogger#warn(Object, Object...)}, and {@link
 * ILogger#error(Object, Object...)} in a normal environment, and all three MUST be visible entries
 * in the logging system. Only {@link ILogger#debug(Object, Object...)} should depend on some
 * special behaviour. This needs to be a "debug" state for the program, and should NOT do anything
 * in other situations.
 */
public interface ILogger {
    /**
     * The invocation to simply output an informative entry to the logging system. This is only to
     * raise awareness of changes that may have occurred internally. This should NOT be used to
     * inform the user of problematic changes.
     *
     * @param to_string   An object that will be converted into a {@link String}, then passed into
     *                    {@link String#format(String, Object...)}.
     * @param format_args The arguments that will be given to {@link String#format(String,
     *                    Object...)}.
     */
    void info(@NotNull final Object to_string, @Nullable final Object... format_args);

    /**
     * The invocation to output a warning entry to the logging system. This should be used to raise
     * awareness that something outside of the norm has occurred, but it is within the realm of
     * expectation, and has been handled by the code. Ideally, you should be as precise as possible
     * to allow the user to fix/correct their behaviour.
     *
     * @param to_string   An object that will be converted into a {@link String}, then passed into
     *                    {@link String#format(String, Object...)}.
     * @param format_args The arguments that will be given to {@link String#format(String,
     *                    Object...)}.
     */
    void warn(@NotNull final Object to_string, @Nullable final Object... format_args);

    /**
     * The invocation to output an error entry to the logging system, This should be used when
     * unpredictable, and/or unhandled behaviour occurs within the code, leading to undefined
     * behaviour for the entire application. It should be accompanied by an {@link
     * Exception#printStackTrace()}, so that ample information can be given to the developer on how
     * to fix the issue.
     *
     * @param to_string   An object that will be converted into a {@link String}, then passed into
     *                    {@link String#format(String, Object...)}.
     * @param format_args The arguments that will be given to {@link String#format(String,
     *                    Object...)}.
     */
    void error(@NotNull final Object to_string, @Nullable final Object... format_args);

    /**
     * The invocation to output a debugging entry to the logging system. This should NOT be visible
     * in normal environments, and only visible if certain conditions occur, ie the application is
     * in a debugging state. This does not follow any specific convention beyond such, as each dev
     * has a different perspective on debugging.
     *
     * @param to_string   An object that will be converted into a {@link String}, then passed into
     *                    {@link String#format(String, Object...)}.
     * @param format_args The arguments that will be given to {@link String#format(String,
     *                    Object...)}.
     */
    void debug(@NotNull final Object to_string, @Nullable final Object... format_args);

    /**
     * Creates a child logger which inherits the name of the current logger. This allows you to
     * create more specific logging instances whose functionally still tie back to a greater logger
     * instance, and by proxy, a specific mod. This should be preferred over creating differing
     * instances for each area you need an {@link ILogger}.
     *
     * @param name The name of the new logger. This should contain a part of the current logger
     *             name.
     *
     * @return An instance of {@link ILogger}, which is semantically tied back to this logger.
     */
    @NotNull
    ILogger child(@NotNull final String name);
}
