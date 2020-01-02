package com.github.frontear.efkolia.impl.configuration;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.utilities.randomizer.PseudoRandom;
import java.nio.file.*;
import lombok.val;
import org.junit.jupiter.api.*;

class ConfigTest {
    static Path file;
    static Config config;
    static TestObject object;

    @BeforeAll
    static void beforeAll() {
        file = Paths.get(System.getProperty("java.io.tmpdir"), "test.json");
        config = new Config(new Logger("Test"),
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