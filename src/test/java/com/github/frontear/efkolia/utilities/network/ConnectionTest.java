package com.github.frontear.efkolia.utilities.network;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.utilities.network.responses.StringResponse;
import java.net.*;
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
        assertThrows(NullPointerException.class, () -> Connection.get(null, null));
        assertThrows(NullPointerException.class, () -> Connection.get("http://httpbin.org/", null));
        assertThrows(NullPointerException.class, () -> Connection.get(null, new StringResponse()));
        assertThrows(UnknownHostException.class,
            () -> Connection.get("http://frontear-efkolia.org/", new StringResponse()));
        assertThrows(MalformedURLException.class, () -> Connection.get("", new StringResponse()));

        val response = assertDoesNotThrow(
            () -> Connection.get("http://httpbin.org/", new StringResponse()));
        assertNotNull(response);

        logger.debug(response);
    }
}