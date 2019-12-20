package com.github.frontear.efkolia.events;

import com.github.frontear.internal.NotNull;

/**
 * The interface which defines an execution system, where a specific class type is registered as a
 * key, and is used to invoke specific collections of methods from across the java code. This
 * follows the traditional design philosophy of an event bus, and is named such.
 *
 * @param <E> The class type whose children shall serve as keys for many method invocations
 */
public interface IEventExecutor<E> {
    /**
     * Scours the instance to find any methods marked with {@link Listener}, then registers them.
     * Ideally, these methods should return void, and be private, as they are not meant to be
     * executed by the user code
     *
     * @param instance The instance to search, and register methods from
     */
    void register(@NotNull final Object instance);

    /**
     * Removes any methods marked with {@link Listener} from the current executor.
     *
     * @param instance The instance to search, and unregister methods from
     */
    void unregister(@NotNull final Object instance);

    /**
     * Grabs all methods that are bound to the specific event, and executes them. It should handle
     * errors quietly, logging them but continuing execution.
     *
     * @param event The event/key that methods are bound to
     * @param <E1>  The extending type of the base event type
     *
     * @return The instance of the event passed in through the parameter, for convenience
     */
    <E1 extends E> E1 fire(@NotNull final E1 event);
}