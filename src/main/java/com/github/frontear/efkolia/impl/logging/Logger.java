package com.github.frontear.efkolia.impl.logging;

import com.github.frontear.efkolia.Properties;
import com.github.frontear.efkolia.api.logging.ILogger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.github.frontear.internal.*;
import java.util.function.Consumer;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;

public final class Logger implements ILogger {
    private final String name;
    private final String prefix;
    private final org.apache.logging.log4j.Logger logger;

    public Logger(@NonNull final MinecraftMod mod) {
        this(mod.getMetadata().getName());
    }

    private Logger(@NonNull final String name) {
        this.name = name;
        this.prefix = Properties.LEGACY_LOGGER ? "[" + name + "]" : "";
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
            this.info(to_string, format_args);
        }
    }

    private void log(@NonNull Consumer<String> callback, @NonNull final Object to_string,
        @Nullable final Object... format_args) {
        callback.accept(prefix + String.format(to_string.toString(), format_args));
    }

    @NotNull
    @Override
    public Logger child(@NonNull final String name) {
        return new Logger(this.name + "/" + name);
    }
}
