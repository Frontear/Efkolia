package com.github.frontear.efkolia.impl.metadata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class FormattedMetadataTest {
    static final int REPEAT = 3;

    static String[] modid, name, version, description;
    static String[][] author, contributors;
    static String[] formatted_name, formatted_author_list, formatted_contributors_list;
    static int i;

    FormattedMetadata metadata;

    @BeforeAll
    static void beforeAll() {
        modid = new String[]{"test_mod", "dummy_mod", "infinity"};
        name = new String[]{"Test Mod", "Dummy Mod", "Infinity"};
        version = new String[]{"0.1.2", "1.0.1", "0.2.9"};
        description = new String[]{"A test mod", "A dummy mod", "A performant utility mod"};

        author = new String[][]{new String[]{"Frontear"}, new String[]{"Ali", "Rizvi"}, new String[]{"Alpha", "Beta", "Charlie"}};
        contributors = new String[][]{new String[]{"Kontorted"}, new String[]{"Kuroda", "Atsushi"}, new String[]{"Alex", "Bob", "Carl"}};

        formatted_name = new String[]{"Test Mod v0.1.2", "Dummy Mod v1.0.1", "Infinity v0.2.9"};
        formatted_author_list = new String[]{"Frontear", "Ali, and Rizvi", "Alpha, Beta, and Charlie"};
        formatted_contributors_list = new String[]{"Kontorted", "Kuroda, and Atsushi", "Alex, Bob, and Carl"};

        i = 0;
    }

    @BeforeEach
    void setUp() {
        metadata = assertDoesNotThrow(() -> new FormattedMetadata(modid[i], name[i], version[i], description[i], author[i], contributors[i]));
    }

    @AfterEach
    void tearDown() {
        if (++i > REPEAT - 1) {
            i = 0;
        }
    }

    @RepeatedTest(value = REPEAT)
    void getFormattedName() {
        assertNotNull(metadata.getFormattedName());
        assertEquals(formatted_name[i], metadata.getFormattedName());
    }

    @RepeatedTest(value = REPEAT)
    void getFormattedAuthorList() {
        assertNotNull(metadata.getFormattedAuthorList());
        assertEquals(formatted_author_list[i], metadata.getFormattedAuthorList());
    }

    @RepeatedTest(value = REPEAT)
    void getFormattedContributorList() {
        assertNotNull(metadata.getFormattedContributorList());
        assertEquals(formatted_contributors_list[i], metadata.getFormattedContributorList());
    }
}