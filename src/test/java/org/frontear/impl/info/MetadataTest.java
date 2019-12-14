package org.frontear.impl.info;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class MetadataTest {
    static Metadata metadata;

    @BeforeAll
    static void beforeAll() {
        metadata = new Metadata("Test", "0.1", "This is just a test", "Frontear", "N/A");
    }

    @Test
    void getName() {
        assertNotNull(metadata.getName());
        assertEquals(metadata.getName(), "Test");
    }

    @Test
    void getVersion() {
        assertNotNull(metadata.getVersion());
        assertEquals(metadata.getVersion(), "0.1");
    }

    @Test
    void getDescription() {
        if (metadata.getDescription() != null) {
            assertEquals(metadata.getDescription(), "This is just a test");
        }
    }

    @Test
    void getAuthors() {
        assertNotNull(metadata.getAuthors());
        assertEquals(metadata.getAuthors(), "Frontear, and N/A");
    }
}