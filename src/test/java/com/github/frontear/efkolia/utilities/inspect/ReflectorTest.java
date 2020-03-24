package com.github.frontear.efkolia.utilities.inspect;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import lombok.*;
import org.junit.jupiter.api.*;

class ReflectorTest {
    static MappingResolver resolver;
    static TestClass instance;

    @BeforeEach
    void setUp() {
        resolver = new TestResolver();
        instance = new TestClass();
    }

    @Test
    @SneakyThrows(ReflectiveOperationException.class)
    void getField() {
        assertThrows(NullPointerException.class, () -> Reflector.getField(null, null));

        Field field = assertDoesNotThrow(() -> {
            val obf_name = resolver.resolveField(
                resolver.resolveClass("com.github.frontear.efkolia.utilities.inspect",
                    "TestSuperclass"),
                "i", "I");
            val f = Reflector.getField(TestClass.class, obf_name);
            f.setAccessible(true);

            return f;
        });

        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertSame(field.getType(), int.class);
        assertEquals(field.getInt(instance), 101);

        field = assertDoesNotThrow(() -> {
            val obf_name = resolver.resolveField(
                resolver.resolveClass("com.github.frontear.efkolia.utilities.inspect", "TestClass"),
                "s", "Ljava/lang/String;");
            val f = Reflector.getField(TestClass.class, obf_name);
            f.setAccessible(true);

            return f;
        });

        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertSame(field.getType(), String.class);
        assertEquals(field.get(instance), "hello");

        assertThrows(NoSuchFieldException.class, () -> {
            val obf_name = resolver.resolveField(
                resolver.resolveClass("com.github.frontear.efkolia.utilities.inspect",
                    "TestSuperclass"), "s", "Ljava/lang/String;");
            Reflector.getField(TestSuperclass.class, obf_name);
        });
    }

    @Test
    @SneakyThrows(ReflectiveOperationException.class)
    void getMethod() {
        assertThrows(NullPointerException.class, () -> Reflector.getMethod(null, null));

        Method method = assertDoesNotThrow(() -> {
            val obf_name = resolver.resolveMethod(
                resolver.resolveClass("com.github.frontear.efkolia.utilities.inspect",
                    "TestSuperclass"),
                "i", "()I");
            val m = Reflector.getMethod(TestClass.class, obf_name);
            m.setAccessible(true);

            return m;
        });

        assertTrue(Modifier.isPrivate(method.getModifiers()));
        assertSame(method.getReturnType(), int.class);
        assertEquals(method.invoke(instance), 1);

        method = assertDoesNotThrow(() -> {
            val obf_name = resolver.resolveMethod(
                resolver.resolveClass("com.github.frontear.efkolia.utilities.inspect", "TestClass"),
                "s", "()Ljava/lang/String;");
            val m = Reflector.getMethod(TestClass.class, obf_name);
            m.setAccessible(true);

            return m;
        });

        assertTrue(Modifier.isPrivate(method.getModifiers()));
        assertSame(method.getReturnType(), String.class);
        assertEquals(method.invoke(instance), "hey");

        assertThrows(NoSuchMethodException.class, () -> {
            val obf_name = resolver.resolveMethod(
                resolver.resolveClass("com.github.frontear.efkolia.utilities.inspect",
                    "TestSuperclass"),
                "s", "()Ljava/lang/String;");
            Reflector.getMethod(TestSuperclass.class, obf_name);
        });
    }

    @Test
    void $getClass() {
        assertThrows(NullPointerException.class, () -> Reflector.getClass(null));

        Class<?> clazz = assertDoesNotThrow(() -> {
            val obf_name = resolver
                .resolveClass("com.github.frontear.efkolia.utilities.inspect", "TestClass");
            return Reflector.getClass(obf_name);
        });

        assertSame(clazz, TestClass.class);

        clazz = assertDoesNotThrow(() -> {
            val obf_name = resolver
                .resolveClass("com.github.frontear.efkolia.utilities.inspect", "TestSuperclass");
            return Reflector.getClass(obf_name);
        });

        assertSame(clazz, TestSuperclass.class);

        assertThrows(ClassNotFoundException.class, () -> {
            val obf_name = resolver.resolveClass("com.abc", "Main");
            Reflector.getClass(obf_name);
        });
    }
}