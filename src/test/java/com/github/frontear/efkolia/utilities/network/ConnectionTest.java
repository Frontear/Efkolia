package com.github.frontear.efkolia.utilities.network;

import static org.junit.jupiter.api.Assertions.*;

import com.github.frontear.efkolia.impl.mod.ModTest;
import com.github.frontear.efkolia.utilities.network.responses.StringResponse;
import java.net.*;
import java.nio.file.Path;
import lombok.val;
import org.junit.jupiter.api.*;

class ConnectionTest {
    static Path file;

    @BeforeAll
    static void beforeAll() {
        file = ModTest.getInstance().getDirectory().resolve("100MB.bin");
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
    }

    @Test
    void download() {
        assertThrows(NullPointerException.class, () -> Connection.download(null, null));
        assertThrows(NullPointerException.class,
            () -> Connection.download("https://speed.hetzner.de/100MB.bin", null));
        assertThrows(NullPointerException.class, () -> Connection.download(null, file));

        assertDoesNotThrow(() -> Connection.download("https://speed.hetzner.de/100MB.bin", file));
    }
}