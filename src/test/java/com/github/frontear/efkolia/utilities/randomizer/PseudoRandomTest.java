package com.github.frontear.efkolia.utilities.randomizer;

import static org.junit.jupiter.api.Assertions.*;

import lombok.*;
import org.junit.jupiter.api.Test;

class PseudoRandomTest {
    @Test
    void nextInt() {
        assertThrows(IllegalArgumentException.class, () -> PseudoRandom.nextInt(100, 0));
        assertDoesNotThrow(() -> PseudoRandom.nextInt(0, 0));

        assertTrue(() -> {
            val ret = assertDoesNotThrow(() -> PseudoRandom.nextInt(0, 10));
            return ret >= 0 && ret <= 10;
        });
        assertTrue(() -> {
            val ret = assertDoesNotThrow(() -> PseudoRandom.nextInt(-10, 10));
            return ret >= -10 && ret <= 10;
        });
    }

    @Test
    void nextIndex() {
        assertThrows(NullPointerException.class, () -> PseudoRandom.nextIndex(null));
        assertThrows(IllegalArgumentException.class, () -> PseudoRandom.nextIndex(new Object()));

        val array = new int[100];
        for (var i = 0; i < array.length; i++) {
            array[i] = PseudoRandom.nextInt(0, 100);
        }

        assertTrue(() -> {
            val ret = assertDoesNotThrow(() -> PseudoRandom.nextIndex(array));
            return ret >= 0 && ret <= array.length;
        });
    }

    @Test
    void nextBoolean() {
        assertDoesNotThrow(PseudoRandom::nextBoolean);
    }

    @Test
    void nextChar() {
        assertTrue(() -> {
            val upper = PseudoRandom.nextBoolean();
            val ret = assertDoesNotThrow(() -> PseudoRandom.nextChar(upper));

            return upper ? ret >= 'A' && ret <= 'Z' : ret >= 'a' && ret <= 'z';
        });
    }

    @Test
    void nextString() {
        assertTrue(() -> {
            val len = PseudoRandom.nextInt(0, 10);
            val ret = assertDoesNotThrow(
                () -> PseudoRandom.nextString(len, PseudoRandom.nextBoolean()));

            return ret.length() == len && ret.matches("[A-z]*");
        });
    }
}