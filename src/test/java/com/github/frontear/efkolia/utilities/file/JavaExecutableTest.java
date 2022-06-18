package com.github.frontear.efkolia.utilities.file;

import static org.junit.jupiter.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JavaExecutableTest {
    static JavaExecutable executable;

    @BeforeAll
    static void beforeAll() {
        executable = new JavaExecutable(JavaExecutableTest.class);
    }

    @Test
    void getResource() {
        assertThrows(NullPointerException.class, () -> executable.getResource(null));

        val reader = executable.getResource("test.txt");
        assertNotNull(reader);
        assertEquals(assertDoesNotThrow(reader::readLine), "hello junit!");
    }

    @AfterAll
    static void afterAll() {
        executable.close();
    }
}
