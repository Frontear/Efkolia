package com.github.frontear.impl.logging;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.utilities.randomizer.PseudoRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class LoggerTest {
    static boolean debug;
    static Logger logger;

    @BeforeAll
    static void beforeAll() {
        debug = ThreadLocalRandom.current().nextBoolean();
        logger = new Logger("Test/Logger", () -> debug);
    }

    @Test
    void info() {
        assertThrows(IllegalFormatConversionException.class,
            () -> logger.info("Hello, %d!", "world"));
        assertThrows(MissingFormatArgumentException.class, () -> logger.info("Hello, %s!"));
        assertThrows(NullPointerException.class, () -> logger.info(null));

        assertDoesNotThrow(() -> logger.info("%s", rand()));
        assertDoesNotThrow(() -> logger.info(rand()));
    }

    @Test
    void warn() {
        assertThrows(IllegalFormatConversionException.class,
            () -> logger.warn("Hello, %d!", "world"));
        assertThrows(MissingFormatArgumentException.class, () -> logger.warn("Hello, %s!"));
        assertThrows(NullPointerException.class, () -> logger.warn(null));

        assertDoesNotThrow(() -> logger.warn("%s", rand()));
        assertDoesNotThrow(() -> logger.warn(rand()));
    }

    @Test
    void error() {
        assertThrows(IllegalFormatConversionException.class,
            () -> logger.error("Hello, %d!", "world"));
        assertThrows(MissingFormatArgumentException.class, () -> logger.error("Hello, %s!"));
        assertThrows(NullPointerException.class, () -> logger.error(null));

        assertDoesNotThrow(() -> logger.error("%s", rand()));
        assertDoesNotThrow(() -> logger.error(rand()));
    }

    @Test
    void debug() {
        if (debug) {
            assertThrows(IllegalFormatConversionException.class,
                () -> logger.debug("Hello, %d!", "world"));
            assertThrows(MissingFormatArgumentException.class, () -> logger.debug("Hello, %s!"));
            assertThrows(NullPointerException.class, () -> logger.debug(null));
        }
        else { // logger isn't meant to execute internally
            assertDoesNotThrow(() -> logger.debug(null));
        }

        assertDoesNotThrow(() -> logger.debug("%s", rand()));
        assertDoesNotThrow(() -> logger.debug(rand()));
    }

    @Test
    void child() {
        assertDoesNotThrow(() -> logger.child("Test"));
        assertNotNull(logger.child("Test"));
    }

    private String rand() {
        return PseudoRandom.nextString(PseudoRandom.nextInt(10, 30), true);
    }
}