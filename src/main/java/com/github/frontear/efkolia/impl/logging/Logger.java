package com.github.frontear.efkolia.impl.logging;

import com.github.frontear.efkolia.Properties;
import com.github.frontear.efkolia.api.logging.ILogger;
import com.github.frontear.internal.*;
import java.util.function.Consumer;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;

public final class Logger implements ILogger {
    private final String name;
    private final org.apache.logging.log4j.Logger logger;

    public Logger(@NonNull final String name) {
        this.name = "[" + name + "] ";
        this.logger = LogManager.getLogger(name);
    }

    @Override
    public void info(@NotNull final Object to_string, @Nullable final Object... format_args) {
        this.log(logger::info, to_string, format_args);
    }

    @Override
    public void warn(@NotNull final Object to_string, @Nullable final Object... format_args) {
        this.log(logger::warn, to_string, format_args);
    }

    @Override
    public void error(@NotNull final Object to_string, @Nullable final Object... format_args) {
        this.log(logger::error, to_string, format_args);
    }

    @Override
    public void debug(@NotNull final Object to_string, @Nullable final Object... format_args) {
        if (Properties.DEBUG) {
            this.log(logger::debug, to_string, format_args);
        }
    }

    private void log(@NonNull Consumer<String> callback, @NonNull final Object to_string,
        @Nullable final Object... format_args) {
        callback.accept(name + String.format(to_string.toString(), format_args));
    }

    @NotNull
    @Override
    public Logger child(@NonNull final String name) {
        return new Logger(this.name + "/" + name);
    }
}
