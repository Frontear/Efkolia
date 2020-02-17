package com.github.frontear.efkolia.utilities.network;

import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.utilities.network.responses.StringResponse;
import lombok.val;
import org.junit.jupiter.api.*;

class ConnectionTest {
    Logger logger;

    @BeforeEach
    void setUp() {
        logger = new Logger("Connection");
    }

    @Test
    void get() {
        val response = Connection.get("http://httpbin.org/", new StringResponse());
        logger.info(response);
    }
}