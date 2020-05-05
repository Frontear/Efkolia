package com.github.frontear.efkolia.utilities.network.responses;

import com.github.frontear.efkolia.utilities.network.Response;
import com.github.frontear.internal.NotNull;
import com.google.gson.*;
import lombok.NonNull;

public final class JsonResponse implements Response<JsonElement> {
    private final JsonParser parser = new JsonParser();

    @NotNull
    @Override
    public JsonElement parse(@NonNull final String response) {
        return parser.parse(response);
    }
}
