package com.github.frontear.impl.events;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.impl.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;
import lombok.val;
import org.junit.jupiter.api.*;

@SuppressWarnings("ConstantConditions")
class EventExecutorTest {
    static EventExecutor executor;
    static TestEvent event;
    static TestObject object;

    @BeforeAll
    static void beforeAll() {
        executor = new EventExecutor(
            new Logger("Test", () -> ThreadLocalRandom.current().nextBoolean()));
        event = new TestEvent("Test", 123);
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
        val last_string = event.string;
        val last_number = event.number;

        assertDoesNotThrow(() -> executor.fire(event));

        assertNotSame(event.string, last_string);
        assertNotSame(event.number, last_number);
    }
}