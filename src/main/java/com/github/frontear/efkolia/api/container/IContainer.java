package com.github.frontear.efkolia.api.container;

import com.github.frontear.internal.NotNull;
import java.util.*;
import java.util.stream.Stream;

/**
 * A container for a collection of items. This is similar to a java collection, such as a {@link
 * List}, however it extends more functionality beyond simple containment. Ideally, it serves to
 * control the full lifecycle of the objects, as well as control various functionality of the
 * objects. It should not expose the objects core functionality or its own functionality.
 *
 * @param <T> The type of the contained object. Expected to be a parent class of sorts.
 */
public interface IContainer<T> {
    /**
     * Attempts to search for a type which extends the parent managed type. If found, it is to be
     * returned, otherwise a {@link NoSuchElementException} should be thrown.
     *
     * @param target The target type to find.
     * @param <T1>   The generic of the target type.
     *
     * @return An instance of the target type.
     */
    @NotNull
    <T1 extends T> T1 get(@NotNull final Class<T1> target);

    /**
     * @return A {@link Stream} of the contained instances.
     */
    @NotNull
    Stream<T> stream();
}
