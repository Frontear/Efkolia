package com.github.frontear.efkolia.utilities.network.responses;

import com.github.frontear.efkolia.utilities.network.Response;
import com.google.gson.*;

public final class JsonResponse implements Response<JsonObject> {
    private final JsonParser parser = new JsonParser();

    @Override
    public JsonObject parse(final String response) {
        return parser.parse(response).getAsJsonObject();
    }
}
