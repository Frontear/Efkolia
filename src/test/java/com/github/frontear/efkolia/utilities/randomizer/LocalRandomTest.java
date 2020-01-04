package com.github.frontear.efkolia.utilities.randomizer;

import static org.junit.jupiter.api.Assertions.*;

import lombok.*;
import org.junit.jupiter.api.Test;

class LocalRandomTest {
    @Test
    void nextInt() {
        assertThrows(IllegalArgumentException.class, () -> LocalRandom.nextInt(100, 0));
        assertDoesNotThrow(() -> LocalRandom.nextInt(0, 0));

        assertTrue(() -> {
            val ret = assertDoesNotThrow(() -> LocalRandom.nextInt(0, 10));
            return ret >= 0 && ret <= 10;
        });
        assertTrue(() -> {
            val ret = assertDoesNotThrow(() -> LocalRandom.nextInt(-10, 10));
            return ret >= -10 && ret <= 10;
        });
    }

    @Test
    void nextIndex() {
        assertThrows(NullPointerException.class, () -> LocalRandom.nextIndex(null));
        assertThrows(IllegalArgumentException.class, () -> LocalRandom.nextIndex(new Object()));

        val array = new int[100];
        for (var i = 0; i < array.length; i++) {
            array[i] = LocalRandom.nextInt(0, 100);
        }

        assertTrue(() -> {
            val ret = assertDoesNotThrow(() -> LocalRandom.nextIndex(array));
            return ret >= 0 && ret <= array.length;
        });
    }

    @Test
    void nextBoolean() {
        assertDoesNotThrow(LocalRandom::nextBoolean);
    }

    @Test
    void nextChar() {
        assertTrue(() -> {
            val upper = LocalRandom.nextBoolean();
            val ret = assertDoesNotThrow(() -> LocalRandom.nextChar(upper));

            return upper ? ret >= 'A' && ret <= 'Z' : ret >= 'a' && ret <= 'z';
        });
    }

    @Test
    void nextString() {
        assertTrue(() -> {
            val len = LocalRandom.nextInt(0, 10);
            val ret = assertDoesNotThrow(
                () -> LocalRandom.nextString(len, LocalRandom.nextBoolean()));

            return ret.length() == len && ret.matches("[A-z]*");
        });
    }
}