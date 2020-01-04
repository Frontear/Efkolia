package com.github.frontear.efkolia.impl.events;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.common.DummyMod;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class EventExecutorTest {
    static EventExecutor executor;
    static TestEvent event;
    static TestObject object;

    @BeforeAll
    static void beforeAll() {
        executor = new EventExecutor(DummyMod.getInstance());
        event = new TestEvent(executor, DummyMod.getInstance());
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