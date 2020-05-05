package com.github.frontear.efkolia.utilities.network.responses;

import static org.junit.jupiter.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.Test;

class JsonResponseTest {
    String test = "{ \"property\": 123 }";

    @Test
    void parse() {
        val json = new JsonResponse().parse(test).getAsJsonObject();

        assertNotNull(json);
        assertTrue(json.has("property"));
        assertEquals(json.get("property").getAsInt(), 123);
    }
}