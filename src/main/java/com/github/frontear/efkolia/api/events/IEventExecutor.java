package com.github.frontear.efkolia.api.events;

import com.github.frontear.internal.NotNull;
import java.util.function.Consumer;

/**
 * The interface which defines an execution system, where a specific type registers as a key, and is
 * used to invoke specific collections of methods from across the java code. This follows the
 * traditional design philosophy of an event bus, and is named such.
 *
 * @param <E> The class type whose children shall serve as keys for many method invocations.
 */
public interface IEventExecutor<E> {
    /**
     * Scours the instance to find any methods marked with {@link Listener}, then registers them.
     * Ideally, these methods should return void, and be private, as they are not meant to be
     * executed by the user code.
     *
     * @param instance The instance to search, and register methods from.
     */
    void register(@NotNull final Object instance);

    /**
     * Registers a listener via direct key,value relations. This is significantly easier as it
     * removes the necessity for a wrapper object to contain events. In the same way, its lifecycle
     * is significantly more controllable
     *
     * @param event    The event to register to
     * @param listener The listener to invoke when the event is {@link IEventExecutor#fire(Object)}
     * @param <E1>     An extended type of your event
     */
    <E1 extends E> void register(@NotNull final Class<E1> event,
        @NotNull final Consumer<E1> listener);

    /**
     * Removes any methods marked with {@link Listener} from the current executor. If an event
     * executing, the instance must be marked for unregistering, and be finalized once the execution
     * has finished.
     *
     * @param instance The instance to search, and unregister methods from.
     */
    void unregister(@NotNull final Object instance);

    /**
     * Unregisters a listener in a key,value relationship. If the event is currently executing, it
     * will continue one final time.
     *
     * @param event    The event to search through for the listener
     * @param listener The listener to be removed
     * @param <E1>     An extended type of your event
     */
    <E1 extends E> void unregister(@NotNull final Class<E1> event,
        @NotNull final Consumer<E1> listener);

    /**
     * Grabs all methods that are bound to the specific event, and executes them. It should handle
     * errors quietly, logging them but continuing execution.
     *
     * @param event The event/key methods bind to.
     * @param <E1>  The extending type of the base event type.
     *
     * @return The instance of the event passed in through the parameter, for convenience.
     */
    @NotNull
    <E1 extends E> E1 fire(@NotNull final E1 event);
}
