package org.frontear.internal;

import java.lang.annotation.*;

/**
 * Represents something that can never be null. This can be used on parameters, and/or methods.
 * Please see <a>https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html#notnull</a>
 * for more information, including how to set these up
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
public @interface NotNull {
}
