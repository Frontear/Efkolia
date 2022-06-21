package com.github.frontear.efkolia.impl.metadata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class MetadataTest {
    static String[] modid, name, version, description;
    static String[][] authors, contributors;
    static String[] formatted_name, formatted_author_list, formatted_contributor_list;

    Metadata metadata;
    int rep;

    @BeforeAll
    static void beforeAll() {
        modid = new String[]{"test_mod", "dummy_mod", "abc123", "qwerty"};
        name = new String[]{"Test Mod", "Dummy Mod", "ABC123", "QWERTY"};
        version = new String[]{"0.0.1", "0.1.0", "1.2.3", "0.2.8"};
        description = new String[]{null, "A dummy mod", null, "dvorak"};

        authors = new String[][]{new String[]{"Frontear"}, new String[]{"John", "Doe"}, new String[]{"Alpha", "Beta", "Charlie"}, new String[]{"Tom", "Brady", "Kevin", "Hart"}};
        contributors = new String[][]{new String[]{"Alex", "Developer123"}, null, null, new String[]{"John"}};

        formatted_name = new String[]{"Test Mod v0.0.1", "Dummy Mod v0.1.0", "ABC123 v1.2.3", "QWERTY v0.2.8"};
        formatted_author_list = new String[]{"Frontear", "John, and Doe", "Alpha, Beta, and Charlie", "Tom, Brady, Kevin, and Hart"};
        formatted_contributor_list = new String[]{"Alex, and Developer123", null, null, "John"};
    }

    @BeforeEach
    void setUp() {
        metadata = new Metadata(modid[rep], name[rep], version[rep], description[rep], authors[rep], contributors[rep]);
    }

    @AfterEach
    void tearDown() {
        if (++rep > 3) { // testing iterates till 4
            rep = 0;
        }
    }

    @RepeatedTest(value = 4)
    void getModId() {
        assertNotNull(metadata.getModId());
        assertEquals(modid[rep], metadata.getModId());
    }

    @RepeatedTest(value = 4)
    void getName() {
        assertNotNull(metadata.getName());
        assertEquals(name[rep], metadata.getName());
    }

    @RepeatedTest(value = 4)
    void getVersion() {
        assertNotNull(metadata.getVersion());
        assertEquals(version[rep], metadata.getVersion());
    }

    @RepeatedTest(value = 4)
    void getFormattedName() {
        assertNotNull(metadata.getFormattedName());
        assertEquals(formatted_name[rep], metadata.getFormattedName());
    }

    @RepeatedTest(value = 4)
    void getDescription() {
        if (description[rep] == null) {
            assertNull(metadata.getDescription());
        } else {
            assertNotNull(metadata.getDescription());
        }

        assertEquals(description[rep], metadata.getDescription());
    }

    @RepeatedTest(value = 4)
    void getAuthors() {
        assertNotNull(metadata.getAuthors());
        assertEquals(authors[rep], metadata.getAuthors());
    }

    @RepeatedTest(value = 4)
    void getFormattedAuthorList() {
        assertNotNull(metadata.getFormattedAuthorList());
        assertEquals(formatted_author_list[rep], metadata.getFormattedAuthorList());
    }

    @RepeatedTest(value = 4)
    void getContributors() {
        if (contributors[rep] == null) {
            assertNull(metadata.getContributors());
        } else {
            assertNotNull(metadata.getContributors());
        }

        assertEquals(contributors[rep], metadata.getContributors());
    }

    @RepeatedTest(value = 4)
    void getFormattedContributorList() {
        if (formatted_contributor_list[rep] == null) {
            assertNull(metadata.getFormattedContributorList());
        } else {
            assertNotNull(metadata.getFormattedContributorList());
        }

        assertEquals(formatted_contributor_list[rep], metadata.getFormattedContributorList());
    }
}