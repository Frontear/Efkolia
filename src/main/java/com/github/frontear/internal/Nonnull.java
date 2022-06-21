package com.github.frontear.internal;

import java.lang.annotation.Documented;

/**
 * Represents an element that cannot be null. This is a guarantee that the element will never be null. How this is
 * achieved is up to the discretion of the developer. It also means that null-safety checks are not required for
 * elements marked by this annotation.
 */
@Documented
public @interface Nonnull {
}
