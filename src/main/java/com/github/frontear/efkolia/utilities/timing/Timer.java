package com.github.frontear.efkolia.utilities.timing;

import com.github.frontear.internal.NotNull;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

/**
 * A utility which tracks lapses of time to the accuracy of nanoseconds. It makes use of {@link
 * System#nanoTime()} to achieve high precision timing. Furthermore, it allows you to seamlessly
 * convert the time information into other types, by making use of {@link TimeUnit}.
 */
public final class Timer {
    private long nanos;

    /**
     * Creates a new instance of a timer, setting the initial time to the current value of {@link
     * System#nanoTime()}.
     */
    public Timer() {
        this.reset();
    }

    /**
     * Resets the initial time to the most recent value received from {@link System#nanoTime()}.
     */
    public void reset() {
        this.nanos = System.nanoTime();
    }

    /**
     * Checks if the elapsed time is equivalent to a specific unit of time and its duration. This
     * internally uses {@link #getElapsed(TimeUnit)}.
     *
     * @param unit The unit to compare with.
     * @param time The elapsed value. This MUST match the specified unit.
     *
     * @return Whether enough time has elapsed.
     */
    public boolean hasElapsed(@NonNull final TimeUnit unit, final long time) {
        return getElapsed(unit) >= time;
    }

    /**
     * Converts the elapsed time to a specified {@link TimeUnit}.
     *
     * @param unit The specified unit type.
     *
     * @return An elapsed value converted into the specified units.
     */
    public long getElapsed(@NonNull final TimeUnit unit) {
        return unit.convert(System.nanoTime() - nanos, TimeUnit.NANOSECONDS);
    }

    /**
     * Converts the elapsed nanosecond time into a time string. By default, this will return the
     * format "HH:mm:ss:SSS".
     *
     * @return A formatted time string.
     */
    @NotNull
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d:%03d", getElapsed(TimeUnit.HOURS) % 24,
            getElapsed(TimeUnit.MINUTES) % 60, getElapsed(TimeUnit.SECONDS) % 60,
            getElapsed(TimeUnit.MILLISECONDS) % 1000);
    }
}
