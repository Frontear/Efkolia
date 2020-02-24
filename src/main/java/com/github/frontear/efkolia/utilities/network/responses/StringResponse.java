package com.github.frontear.efkolia.utilities.network.responses;

import com.github.frontear.efkolia.utilities.network.Response;
import com.github.frontear.internal.NotNull;
import lombok.NonNull;

public final class StringResponse implements Response<String> {
    @NotNull
    @Override
    public String parse(@NonNull final String response) {
        return response;
    }
}
