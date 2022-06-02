package com.github.frontear.efkolia.utilities.timing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

class TimerTest {
    static Timer timer;

    @BeforeAll
    static void beforeAll() {
        timer = new Timer();
    }

    @BeforeEach
    void setUp() {
        timer.reset();
    }

    @Test
    void reset() {
        timer.reset();
        assertEquals(0, timer.getElapsed(
            TimeUnit.MILLISECONDS)); // the nanoseconds can be slightly inaccurate based on system performance
    }

    @Test
    void hasElapsed() {
        assertThrows(NullPointerException.class, () -> timer.hasElapsed(null, 0));

        this.sleep();
        assertTrue(timer.hasElapsed(TimeUnit.SECONDS, 1));
    }

    @Test
    void getElapsed() {
        assertThrows(NullPointerException.class, () -> timer.getElapsed(null));

        this.sleep();
        assertEquals(1, timer.getElapsed(TimeUnit.SECONDS));
    }

    @Test
    void testToString() {
        this.sleep();
        assertEquals("00:00:01", timer.toString().substring(0,
            8)); // the milliseconds can be slightly inaccurate based on system performance
    }

    @SneakyThrows(InterruptedException.class)
    private void sleep() {
        Thread.sleep(1000);
    }
}