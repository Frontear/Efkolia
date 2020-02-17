package com.github.frontear.efkolia.utilities.network.responses;

import static org.junit.jupiter.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.Test;

class StringResponseTest {
    String test = "123";

    @Test
    void parse() {
        val string = new StringResponse().parse(test);

        assertNotNull(string);
        assertEquals(string, "123");
    }
}