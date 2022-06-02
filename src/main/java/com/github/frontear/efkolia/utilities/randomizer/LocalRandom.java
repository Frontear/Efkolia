package com.github.frontear.efkolia.utilities.randomizer;

import com.github.frontear.efkolia.Properties;
import com.github.frontear.internal.NotNull;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.*;
import lombok.experimental.UtilityClass;

/**
 * A utility class which generates random numbers in a fully thread-safe manner. By default, it will
 * internally use {@link ThreadLocalRandom} to generate pseudo-random, though
 * {@link Properties#SECURE_RANDOM} can be set to make use of {@link SecureRandom}.
 */
@UtilityClass
public class LocalRandom {
    private final Random random =
        Properties.SECURE_RANDOM ? new SecureRandom()
            : ThreadLocalRandom.current();
    private final char[] alphas = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    static {
        val dummy = new byte[1];
        random.nextBytes(dummy); // force seeding
    }

    /**
     * Generates a random integer value between the min and max, inclusive.
     *
     * @param min The minimum value to generate at or higher.
     * @param max The maximum value to generate at or lower.
     *
     * @return A random value between the bounds.
     */
    public int nextInt(final int min, final int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates a random index value based on the length of the specified array.
     *
     * @param array The array to generate a random index for.
     *
     * @return The randomized index selection.
     */
    public int nextIndex(@NonNull final Object array) {
        return nextInt(0, Array.getLength(array) - 1);
    }

    /**
     * Generates a random value of an int, then does a weak conversion to boolean through equality.
     *
     * @return A random boolean value.
     */
    public boolean nextBoolean() {
        return nextInt(0, 1) != 0;
    }

    /**
     * Generates a random alphabetic character.
     *
     * @param upper Whether the character should be an uppercase letter or not.
     *
     * @return The random alphabetic character.
     */
    public char nextChar(final boolean upper) {
        val character = alphas[nextIndex(alphas)];

        return upper ? Character.toUpperCase(character) : character;
    }

    /**
     * Generates a random string based on a specified length. It internally makes use of
     * {@link #nextChar(boolean)} to achieve this.
     *
     * @param len         The length of the string.
     * @param random_case Whether the string should have randomized casing or not.
     *
     * @return The randomly generated string.
     */
    @NotNull
    public String nextString(int len, final boolean random_case) {
        val string = new StringBuilder(len);

        while (len-- > 0) {
            string.append(nextChar(random_case && nextBoolean()));
        }

        return string.toString();
    }
}
