package com.github.frontear.efkolia.utilities.inspect;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.utilities.inspect.resolvers.DummyResolver;
import java.lang.reflect.*;
import lombok.*;
import org.junit.jupiter.api.*;

class ReflectorTest {
    static MappingResolver resolver;
    static TestClass instance;

    @BeforeEach
    void setUp() {
        resolver = new DummyResolver();
        instance = new TestClass();
    }

    @Test
    @SneakyThrows(ReflectiveOperationException.class)
    void getField() {
        assertThrows(NullPointerException.class, () -> Reflector.getField(null, null, null, null));

        Field field = assertDoesNotThrow(() -> {
            val f = Reflector.getField(TestClass.class, "i", "I", resolver);
            f.setAccessible(true);

            return f;
        });

        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertSame(field.getType(), int.class);
        assertEquals(field.getInt(instance), 101);

        field = assertDoesNotThrow(() -> {
            val f = Reflector.getField(TestClass.class, "s", "Ljava/lang/String;", resolver);
            f.setAccessible(true);

            return f;
        });

        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertSame(field.getType(), String.class);
        assertEquals(field.get(instance), "hello");

        assertThrows(NoSuchFieldException.class,
            () -> Reflector.getField(TestSuperclass.class, "s", "Ljava/lang/String;", resolver));
    }

    @Test
    @SneakyThrows(ReflectiveOperationException.class)
    void getMethod() {
        assertThrows(NullPointerException.class, () -> Reflector.getMethod(null, null, null, null));

        Method method = assertDoesNotThrow(() -> {
            val m = Reflector.getMethod(TestClass.class, "i", "()I", resolver);
            m.setAccessible(true);

            return m;
        });

        assertTrue(Modifier.isPrivate(method.getModifiers()));
        assertSame(method.getReturnType(), int.class);
        assertEquals(method.invoke(instance), 1);

        method = assertDoesNotThrow(() -> {
            val m = Reflector.getMethod(TestClass.class, "s", "()Ljava/lang/String;", resolver);
            m.setAccessible(true);

            return m;
        });

        assertTrue(Modifier.isPrivate(method.getModifiers()));
        assertSame(method.getReturnType(), String.class);
        assertEquals(method.invoke(instance), "hey");

        assertThrows(NoSuchMethodException.class,
            () -> Reflector.getMethod(TestSuperclass.class, "s", "()Ljava/lang/String;", resolver));
    }

    @Test
    void $getClass() {
        assertThrows(NullPointerException.class, () -> Reflector.getClass(null, null, null));

        Class<?> clazz = assertDoesNotThrow(() -> Reflector
            .getClass("com.github.frontear.efkolia.utilities.inspect", "TestClass", resolver));

        assertSame(clazz, TestClass.class);

        clazz = assertDoesNotThrow(() -> Reflector
            .getClass("com.github.frontear.efkolia.utilities.inspect", "TestSuperclass", resolver));

        assertSame(clazz, TestSuperclass.class);

        assertThrows(ClassNotFoundException.class,
            () -> Reflector.getClass("com.abc", "Main", resolver));
    }
}