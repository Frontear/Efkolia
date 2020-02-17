package com.github.frontear.efkolia.utilities.network;

import com.github.frontear.internal.NotNull;

/**
 * Handles the conversion of a connection response
 *
 * @param <T> The converted type
 */
public interface Response<T> {
    /**
     * Performs the conversion of a site response via {@link Connection} into an object
     *
     * @param response The site response to convert
     *
     * @return The converted response as an object
     */
    T parse(@NotNull final String response);
}
