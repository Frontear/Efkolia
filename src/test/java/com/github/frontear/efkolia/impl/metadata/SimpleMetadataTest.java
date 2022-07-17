package com.github.frontear.efkolia.impl.metadata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMetadataTest {
    static String[] modid, name, version, description; // null, String
    static String[][] author, contributors; // null, String[1], String[1 + n]

    @BeforeAll
    static void beforeAll() {
        modid = new String[]{null, "test_mod"};
        name = new String[]{null, "Test Mod"};
        version = new String[]{null, "0.1.2"};
        description = new String[]{null, "A test mod"};

        author = new String[][]{null, new String[]{"Frontear"}, new String[]{"Ali", "Rizvi"}};
        contributors = new String[][]{null, new String[]{"Kontorted"}, new String[]{"Kuroda", "Atsushi"}};
    }

    @Test
    void getModId() {
        assertThrows(NullPointerException.class, () -> new SimpleMetadata(modid[0], name[1], version[1], description[1], author[1], contributors[1])); // null safety

        SimpleMetadata metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getModId()); // null safety
        assertEquals(modid[1], metadata.getModId()); // equality
    }

    @Test
    void getName() {
        assertThrows(NullPointerException.class, () -> new SimpleMetadata(modid[1], name[0], version[1], description[1], author[1], contributors[1])); // null safety

        SimpleMetadata metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getName()); // null safety
        assertEquals(name[1], metadata.getName()); // equality
    }

    @Test
    void getVersion() {
        assertThrows(NullPointerException.class, () -> new SimpleMetadata(modid[1], name[1], version[0], description[1], author[1], contributors[1])); // null safety

        SimpleMetadata metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getVersion()); // null safety
        assertEquals(version[1], metadata.getVersion()); // equality
    }

    @Test
    void getDescription() {
        SimpleMetadata metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[0], author[1], contributors[1])); // nullable prop
        assertNull(metadata.getDescription()); // nullable prop
        assertEquals(description[0], metadata.getDescription()); // equality

        metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1])); // null safety
        assertNotNull(metadata.getDescription()); // null safety
        assertEquals(description[1], metadata.getDescription()); // equality
    }

    @Test
    void getAuthors() {
        assertThrows(NullPointerException.class, () -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[0], contributors[1])); // null safety

        SimpleMetadata metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getAuthors()); // null safety
        assertArrayEquals(author[1], metadata.getAuthors()); // equality
        author[1][0] = "Kontorted"; // manipulation
        assertNotEquals(author[1], metadata.getAuthors()); // ensure no changes

        metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[2], contributors[1]));
        assertNotNull(metadata.getAuthors()); // null safety
        assertArrayEquals(author[2], metadata.getAuthors()); // equality
        author[2][0] = "Kuroda"; // manipulation
        author[2][1] = "Atsushi"; // manipulation
        assertNotEquals(author[2], metadata.getAuthors()); // ensure no changes
    }

    @Test
    void getContributors() {
        SimpleMetadata metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[0])); // nullable prop
        assertNull(metadata.getContributors()); // nullable prop
        assertEquals(contributors[0], metadata.getContributors()); // equality

        metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getContributors()); // null safety
        assertArrayEquals(contributors[1], metadata.getContributors()); // equality
        contributors[1][0] = "Frontear"; // manipulation
        assertNotEquals(contributors[1], metadata.getContributors()); // ensure no changes

        metadata = assertDoesNotThrow(() -> new SimpleMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[2]));
        assertNotNull(metadata.getContributors()); // null safety
        assertArrayEquals(contributors[2], metadata.getContributors()); // equality
        contributors[2][0] = "Ali"; // manipulation
        contributors[2][1] = "Rizvi"; // manipulation
        assertNotEquals(contributors[2], metadata.getContributors()); // ensure no changes
    }
}