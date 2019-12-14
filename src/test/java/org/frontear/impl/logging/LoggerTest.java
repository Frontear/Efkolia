package org.frontear.impl.logging;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class LoggerTest {
    static boolean debug;
    static Logger logger;
    static int lines;
    static Path log;

    BufferedReader reader;

    @BeforeAll
    static void beforeAll() {
        log = Paths.get("logs", "efkolia.log");
        debug = ThreadLocalRandom.current().nextBoolean();
        logger = new Logger("Test", () -> debug);
        lines = 0;
    }

    @AfterAll
    static void afterAll() {
        assertEquals(lines, debug ? 8 : 6);
    }

    @SneakyThrows(IOException.class)
    @BeforeEach
    void setUp() {
        reader = Files.newBufferedReader(log);
    }

    @SneakyThrows(IOException.class)
    @AfterEach
    void tearDown() {
        reader.close();
    }

    @Test
    void info() {
        assertThrows(IllegalFormatConversionException.class,
            () -> logger.info("Hello, %d!", "world"));
        assertThrows(MissingFormatArgumentException.class, () -> logger.info("Hello, %s!"));
        assertThrows(NullPointerException.class, () -> logger.info(null));

        assertDoesNotThrow(() -> logger.info("Hello, %s!", "world"));
        assertDoesNotThrow(() -> logger.info("Hello, world!"));

        lines += 2;
        assertEquals(reader.lines().count(), lines);
    }

    @Test
    void warn() {
        assertThrows(IllegalFormatConversionException.class,
            () -> logger.warn("Hello, %d!", "world"));
        assertThrows(MissingFormatArgumentException.class, () -> logger.warn("Hello, %s!"));
        assertThrows(NullPointerException.class, () -> logger.warn(null));

        assertDoesNotThrow(() -> logger.warn("Hello, %s!", "world"));
        assertDoesNotThrow(() -> logger.warn("Hello, world!"));

        lines += 2;
        assertEquals(reader.lines().count(), lines);
    }

    @Test
    void error() {
        assertThrows(IllegalFormatConversionException.class,
            () -> logger.error("Hello, %d!", "world"));
        assertThrows(MissingFormatArgumentException.class, () -> logger.error("Hello, %s!"));
        assertThrows(NullPointerException.class, () -> logger.error(null));

        assertDoesNotThrow(() -> logger.error("Hello, %s!", "world"));
        assertDoesNotThrow(() -> logger.error("Hello, world!"));

        lines += 2;
        assertEquals(reader.lines().count(), lines);
    }

    @Test
    void debug() {
        if (debug) {
            assertThrows(IllegalFormatConversionException.class,
                () -> logger.debug("Hello, %d!", "world"));
            assertThrows(MissingFormatArgumentException.class, () -> logger.debug("Hello, %s!"));
            assertThrows(NullPointerException.class, () -> logger.debug(null));

            assertDoesNotThrow(() -> logger.debug("Hello, %s!", "world"));
            assertDoesNotThrow(() -> logger.debug("Hello, world!"));
        }
        else { // logger isn't meant to execute internally
            assertDoesNotThrow(() -> logger.debug("Hello, %d!", "world"));
            assertDoesNotThrow(() -> logger.debug("Hello, %s!"));
            assertDoesNotThrow(() -> logger.debug(null));
        }

        lines += debug ? 2 : 0;
        assertEquals(reader.lines().count(), lines);
    }
}