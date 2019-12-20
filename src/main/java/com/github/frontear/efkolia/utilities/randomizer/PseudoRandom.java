package com.github.frontear.efkolia.utilities.randomizer;

import com.github.frontear.internal.NotNull;
import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;
import lombok.experimental.UtilityClass;
import lombok.*;

/**
 * A utility class that allows the generation of pseudo-random numbers in a fully thread-safe
 * manner. It internally uses {@link ThreadLocalRandom} to generate these values
 */
@UtilityClass
public class PseudoRandom {
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final char[] alphas = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Generates a random integer value between min and max, inclusive
     *
     * @param min The minimum value to generate at or higher
     * @param max The maximum value to generate at or lower
     *
     * @return A random value between the bounds
     */
    public int nextInt(final int min, final int max) {
        return random.nextInt(min, max + 1);
    }

    /**
     * Generates a random double value between min and max, inclusive
     *
     * @param min The minimum value to generate at or higher
     * @param max The maximum value to generate at or lower
     *
     * @return A random value between the bounds
     */
    public double nextDouble(final double min, final double max) {
        return random.nextDouble(min, max + 1);
    }

    /**
     * Generates a random index value based on the length of the specified array
     *
     * @param array The array to generate a random index for
     *
     * @return The randomized index selection
     */
    public int nextIndex(@NotNull final Object array) {
        return nextInt(0, Array.getLength(array) - 1);
    }

    /**
     * Generates a random value of an int, then does a weak conversion to boolean through equality
     *
     * @return A random boolean value
     */
    public boolean nextBoolean() {
        return nextInt(0, 1) != 0;
    }

    /**
     * Generates a random alphabetic character
     *
     * @param upper Whether the character should be an uppercase letter or not
     *
     * @return The random alphabetic character
     */
    public char nextChar(final boolean upper) {
        val character = alphas[nextIndex(alphas)];

        return upper ? Character.toUpperCase(character) : character;
    }

    /**
     * Generates a random string based on a specified length. It internally makes use of {@link
     * PseudoRandom#nextChar(boolean)} to achieve this
     *
     * @param len         The length of the string
     * @param random_case Whether the string should have randomized casing or not
     *
     * @return The random string
     */
    public String nextString(final int len, final boolean random_case) {
        val string = new StringBuilder(len);

        for (var i = 0; i < len; ++i) {
            string.append(nextChar(random_case && nextBoolean()));
        }

        return string.toString();
    }
}
