package com.github.frontear.efkolia.impl.metadata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class StrictMetadataTest {
    static String[] modid, name, version; // null, String (conforming), String[n] (non-conforming)
    static String[][] author; // null, String[] (conforming), String[][n] (non-conforming)
    static String[] description;
    static String[][] contributors;

    @BeforeAll
    static void beforeAll() {
        modid = new String[]{null, "test_mod", "tEst_mod", "Test Mod", "t3st m0d", "test-mod"};
        name = new String[]{null, "Test Mod", "test mod", "Mod of Test", "T3st M0d", "test_mod"};
        version = new String[]{null, "0.1.2", "v0.1", "1.2", "3.5.4.6", "ver. 1.1"};
        author = new String[][]{null, new String[]{"Frontear"}, new String[]{"Ali", null, "Rizvi"}, new String[]{"", "\0"}};

        description = new String[]{null, "A test mod", "   ", ""};
        contributors = new String[][]{null, new String[]{"John", "Doe"}, new String[]{"Alpha", null, "Charlie"}, new String[]{"", "\0"}};
    }

    @Test
    void getModId() {
        assertThrows(NullPointerException.class, () -> new StrictMetadata(modid[0], name[1], version[1], description[1], author[1], contributors[1]));

        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[2], name[1], version[1], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[3], name[1], version[1], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[4], name[1], version[1], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[5], name[1], version[1], description[1], author[1], contributors[1]));

        StrictMetadata metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getModId());
        assertEquals(modid[1], metadata.getModId());
    }

    @Test
    void getName() {
        assertThrows(NullPointerException.class, () -> new StrictMetadata(modid[1], name[0], version[1], description[1], author[1], contributors[1]));

        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[2], version[1], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[3], version[1], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[4], version[1], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[5], version[1], description[1], author[1], contributors[1]));

        StrictMetadata metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getName());
        assertEquals(name[1], metadata.getName());
    }

    @Test
    void getVersion() {
        assertThrows(NullPointerException.class, () -> new StrictMetadata(modid[1], name[1], version[0], description[1], author[1], contributors[1]));

        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[2], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[3], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[4], description[1], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[5], description[1], author[1], contributors[1]));

        StrictMetadata metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getVersion());
        assertEquals(version[1], metadata.getVersion());
    }

    @Test
    void getDescription() {
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[1], description[2], author[1], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[1], description[3], author[1], contributors[1]));

        StrictMetadata metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[0], author[1], contributors[1]));
        assertNull(metadata.getDescription());
        assertEquals(description[0], metadata.getDescription());

        metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getDescription());
        assertEquals(description[1], metadata.getDescription());


    }

    @Test
    void getAuthors() {
        assertThrows(NullPointerException.class, () -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[0], contributors[1]));

        assertThrows(NullPointerException.class, () -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[2], contributors[1]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[3], contributors[1]));

        StrictMetadata metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getAuthors());
        assertEquals(author[1], metadata.getAuthors());
    }

    @Test
    void getContributors() {
        assertThrows(NullPointerException.class, () -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[2]));
        assertThrows(InvalidParameterException.class, () -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[3]));

        StrictMetadata metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[0]));
        assertNull(metadata.getContributors());
        assertEquals(contributors[0], metadata.getContributors());

        metadata = assertDoesNotThrow(() -> new StrictMetadata(modid[1], name[1], version[1], description[1], author[1], contributors[1]));
        assertNotNull(metadata.getContributors());
        assertEquals(contributors[1], metadata.getContributors());
    }
}