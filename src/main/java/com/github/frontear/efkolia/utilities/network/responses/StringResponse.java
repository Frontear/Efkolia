package com.github.frontear.efkolia.utilities.network.responses;

import com.github.frontear.efkolia.utilities.network.Response;

public final class StringResponse implements Response<String> {
    @Override
    public String parse(final String response) {
        return response;
    }
}
