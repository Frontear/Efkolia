package com.github.frontear.efkolia.utilities.timing;

import com.github.frontear.internal.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * A timer utility that allows you to time certain durations to the accuracy of nanoseconds. It
 * makes use of {@link System#nanoTime()} to achieve high precision duration timing. Furthermore, it
 * allows you to seamlessly convert the time information into other types, by making use of {@link
 * TimeUnit}.
 */
public class Timer {
    private long nanos;

    public Timer() {
        this.reset();
    }

    public void reset() {
        this.nanos = System.nanoTime();
    }

    public boolean hasElapsed(@NotNull final TimeUnit unit, final long time) {
        return getElapsed(unit) >= time;
    }

    public long getElapsed(@NotNull final TimeUnit unit) {
        return unit.convert(System.nanoTime() - nanos, TimeUnit.NANOSECONDS);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d:%03d", getElapsed(TimeUnit.HOURS) % 24,
            getElapsed(TimeUnit.MINUTES) % 60, getElapsed(TimeUnit.SECONDS) % 60,
            getElapsed(TimeUnit.MILLISECONDS) % 1000);
    }
}
