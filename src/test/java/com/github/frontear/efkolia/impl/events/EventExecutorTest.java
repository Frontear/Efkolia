package com.github.frontear.efkolia.impl.events;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.utilities.randomizer.PseudoRandom;
import lombok.val;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class EventExecutorTest {
    static EventExecutor executor;
    static TestEvent event;
    static TestObject object;

    @BeforeAll
    static void beforeAll() {
        val logger = new Logger("Test");

        executor = new EventExecutor(logger);
        event = new TestEvent(executor, logger);
        object = new TestObject();
    }

    @Test
    void register() {
        assertThrows(NullPointerException.class, () -> executor.register(null));
        assertDoesNotThrow(() -> executor.register(object));
    }

    @Test
    void unregister() {
        assertDoesNotThrow(() -> executor.unregister(object));
    }

    @Test
    void fire() {
        assertDoesNotThrow(() -> executor.fire(event));
    }
}