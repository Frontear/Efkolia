package com.github.frontear.efkolia.impl.events;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.common.DummyMod;
import java.util.function.Consumer;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class EventExecutorTest {
    static EventExecutor executor;
    static TestEvent event;
    static TestObject object;
    static Consumer<TestEvent> listener = x -> System.out.println("TestEvent via Consumer!");

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

        assertThrows(NullPointerException.class, () -> executor.register(null, null));
        assertThrows(NullPointerException.class, () -> executor.register(null, listener));
        assertDoesNotThrow(() -> executor.register(TestEvent.class, listener));
    }

    @Test
    void unregister() {
        assertThrows(NullPointerException.class, () -> executor.unregister(null));
        assertDoesNotThrow(() -> executor.unregister(object));

        assertThrows(NullPointerException.class, () -> executor.unregister(null, null));
        assertThrows(NullPointerException.class, () -> executor.unregister(null, listener));
        assertDoesNotThrow(() -> executor.unregister(TestEvent.class, listener));
    }

    @Test
    void fire() {
        assertThrows(NullPointerException.class, () -> executor.fire(null));
        assertDoesNotThrow(() -> executor.fire(event));
    }
}