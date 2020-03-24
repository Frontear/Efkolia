package com.github.frontear.internal;

import java.lang.annotation.*;

/**
 * Represents something which may be null. This can be used on parameters, and/or methods. Please
 * see <a>https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html#nullable</a> for
 * more information, including how to set these up for IntelliJ IDEA.
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
public @interface Nullable {
}
