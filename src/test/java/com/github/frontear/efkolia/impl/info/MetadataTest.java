package com.github.frontear.efkolia.impl.info;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class MetadataTest {
    static Metadata metadata;

    @BeforeAll
    static void beforeAll() {
        metadata = new Metadata("Test", "0.1", "Frontear", "N/A");
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
    void getDevelopers() {
        assertNotNull(metadata.getDevelopers());
        assertEquals(metadata.getDevelopers(), "Frontear, and N/A");
    }
}