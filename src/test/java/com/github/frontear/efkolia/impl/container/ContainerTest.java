package com.github.frontear.efkolia.impl.container;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.common.DummyMod;
import com.github.frontear.efkolia.impl.container.impl.*;
import org.junit.jupiter.api.*;

class ContainerTest {
    static TestParentContainer container;

    @BeforeAll
    static void beforeAll() {
        container = new TestParentContainer(DummyMod.getInstance());
    }

    @Test
    void get() {
        assertThrows(NullPointerException.class, () -> container.get(null));
        assertNull(container.get(TestParent.class));

        assertNotNull(container.get(FirstTest.class));
        assertNotNull(container.get(SecondTest.class));
        assertNotNull(container.get(ThirdTest.class));
    }

    @Test
    void stream() {
        assertNotNull(container.stream());
        assertEquals(container.stream().count(), 3);

        container.stream().forEach(TestParent::print);
    }
}