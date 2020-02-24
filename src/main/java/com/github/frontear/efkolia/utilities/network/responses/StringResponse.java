package com.github.frontear.efkolia.utilities.network.responses;

import com.github.frontear.efkolia.utilities.network.Response;
import lombok.NonNull;

public final class StringResponse implements Response<String> {
    @Override
    public String parse(@NonNull final String response) {
        return response;
    }
}
