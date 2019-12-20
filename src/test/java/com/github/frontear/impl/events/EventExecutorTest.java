package com.github.frontear.impl.events;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.impl.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class EventExecutorTest {
    static EventExecutor executor;
    static TestEvent event;
    static TestObject object;

    static boolean cancel_normal;

    @BeforeAll
    static void beforeAll() {
        executor = new EventExecutor(
            new Logger("Test", () -> ThreadLocalRandom.current().nextBoolean()));
        event = new TestEvent("Test", 123);
        object = new TestObject();

        cancel_normal = ThreadLocalRandom.current().nextBoolean();
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

        if (cancel_normal) {
            assertSame(event.string, "HIGH");
            assertSame(event.number, 0);
        }
        else { // run last
            assertSame(event.string, "LOW");
            assertSame(event.number, 2);
        }
    }
}