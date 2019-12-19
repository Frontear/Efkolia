package org.frontear.impl.configuration;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ThreadLocalRandom;
import lombok.*;
import org.frontear.impl.logging.Logger;
import org.junit.jupiter.api.*;

class ConfigTest {
    static Path file;
    static Config config;
    TestObject object;

    @BeforeAll
    static void beforeAll() {
        file = Paths.get(System.getProperty("java.io.tmpdir"), "test.json");
        config = new Config(new Logger("Test", () -> ThreadLocalRandom.current().nextBoolean()),
            file);
    }

    @SneakyThrows(IOException.class)
    @AfterAll
    static void afterAll() {
        Files.delete(file);
    }

    @BeforeEach
    void setUp() {
        object = new TestObject();
    }

    @AfterEach
    void tearDown() {
        object = null;
    }

    @Test
    void register() {
        assertDoesNotThrow(() -> config.register(object));
    }

    @Test
    void unregister() {
        assertDoesNotThrow(() -> config.unregister(object));
    }

    @Test
    void load() {
        assertDoesNotThrow(() -> config.load());
    }

    @Test
    void save() {
        val exists = Files.exists(file);

        assertDoesNotThrow(() -> config.save());

        if (!exists) {
            assertTrue(Files.exists(file));
        }
    }
}