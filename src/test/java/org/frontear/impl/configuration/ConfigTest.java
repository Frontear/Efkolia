package org.frontear.impl.configuration;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.*;
import java.util.concurrent.ThreadLocalRandom;
import lombok.val;
import org.frontear.impl.logging.Logger;
import org.junit.jupiter.api.*;

class ConfigTest {
    static Path file;
    static Config config;
    static TestObject object;

    @BeforeAll
    static void beforeAll() {
        file = Paths.get(System.getProperty("java.io.tmpdir"), "test.json");
        config = new Config(new Logger("Test", () -> ThreadLocalRandom.current().nextBoolean()),
            file);
        object = new TestObject();
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