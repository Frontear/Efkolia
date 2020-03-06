package com.github.frontear.efkolia.utilities.inspect.exceptions;

import lombok.NonNull;

public class NoSuchMappingException extends RuntimeException {
    public NoSuchMappingException(@NonNull final String message) {
        super(message);
    }
}
