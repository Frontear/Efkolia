package com.github.frontear.efkolia.impl.events;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.impl.mod.ModTest;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class EventExecutorTest {
    static EventExecutor executor;
    static TestEvent event;
    static TestObject object;

    @BeforeAll
    static void beforeAll() {
        executor = new EventExecutor(ModTest.getInstance());
        event = new TestEvent(executor, ModTest.getInstance());
        object = new TestObject();
    }

    @Test
    void register() {
        assertThrows(NullPointerException.class, () -> executor.register(null));
        assertDoesNotThrow(() -> executor.register(object));
    }

    @Test
    void unregister() {
        assertThrows(NullPointerException.class, () -> executor.unregister(null));
        assertDoesNotThrow(() -> executor.unregister(object));
    }

    @Test
    void fire() {
        assertThrows(NullPointerException.class, () -> executor.fire(null));

        assertDoesNotThrow(() -> executor.fire(event));
    }
}