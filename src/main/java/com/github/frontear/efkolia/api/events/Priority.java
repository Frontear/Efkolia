package com.github.frontear.efkolia.api.events;

/**
 * The priority enumeration for the events. The highest in order is {@link #HIGH}, and the lowest is
 * {@link #LOW}. Event execution must respect this order, with the highest priority executing first,
 * and the lowest executing last through {@link IEventExecutor#fire(Object)}.
 */
public enum Priority {
    LOW,
    NORMAL,
    HIGH
}
