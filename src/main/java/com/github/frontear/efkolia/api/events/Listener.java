package com.github.frontear.efkolia.api.events;

import java.lang.annotation.*;

/**
 * An interface which defines a method that is only meant to run when fired through an {@link
 * IEventExecutor}.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {
    /**
     * The priority of the event. This is by default {@link Priority#NORMAL}. By changing this, you
     * directly affect the order of the invocations listeners execute in via {@link
     * IEventExecutor#fire(Object)}.
     *
     * @return The priority of the event listener.
     */
    Priority value() default Priority.NORMAL;
}
