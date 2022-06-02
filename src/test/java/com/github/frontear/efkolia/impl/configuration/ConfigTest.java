package com.github.frontear.efkolia.impl.configuration;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.impl.mod.ModTest;
import java.nio.file.*;
import lombok.val;
import org.junit.jupiter.api.*;

class ConfigTest {
    static Path file;
    static Config config;
    static TestObject object;

    @BeforeAll
    static void beforeAll() {
        file = ModTest.getInstance().getDirectory().resolve("config.json");
        config = new Config(ModTest.getInstance());
        object = new TestObject();
    }

    @Test
    void register() {
        assertThrows(NullPointerException.class, () -> config.register(null));
        assertDoesNotThrow(() -> config.register(object));
    }

    @Test
    void unregister() {
        assertThrows(NullPointerException.class, () -> config.unregister(null));
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