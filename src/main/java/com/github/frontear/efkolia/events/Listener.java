package com.github.frontear.efkolia.events;

import java.lang.annotation.*;

/**
 * An interface which defines a method that is only meant to run when fired through an {@link
 * IEventExecutor}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {
    Priority value() default Priority.NORMAL;
}
