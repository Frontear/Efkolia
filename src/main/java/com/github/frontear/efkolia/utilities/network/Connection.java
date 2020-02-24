package com.github.frontear.efkolia.utilities.network;

import java.io.*;
import java.net.*;
import lombok.*;
import lombok.experimental.UtilityClass;

/**
 * A simple utility class that allows you to grab various information from a website. It internally
 * makes use of {@link HttpURLConnection}.
 */
@UtilityClass
public class Connection {
    /**
     * Performs a GET request to a specific site, and returns a response in a compatible {@link
     * Response} format.
     *
     * @param url  The site URL. This is wrapped with {@link URL} internally.
     * @param type The response type. This is used to easily convert responses into objects.
     * @param <T>  The type you expect to manipulate.
     *
     * @return The expected response formatted as the type.
     */
    @SneakyThrows(IOException.class)
    public <T> T get(@NonNull final String url, @NonNull final Response<T> type) {
        val connect = (HttpURLConnection) new URL(url).openConnection();
        connect.setRequestMethod("GET");

        val response = new StringBuilder();

        try (val reader = new BufferedReader(new InputStreamReader(connect.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                response.append(line).append("\n"); // why is there no appendLine smh
            }
        }

        return type.parse(response.toString());
    }
}
