package com.github.frontear.efkolia.impl.logging;

import com.github.frontear.efkolia.Properties;
import com.github.frontear.efkolia.api.logging.ILogger;
import com.github.frontear.internal.*;
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
    public void info(@NonNull final Object to_string, @Nullable final Object... format_args) {
        logger.info(name + String.format(to_string.toString(), format_args));
    }

    @Override
    public void warn(@NonNull final Object to_string, @Nullable final Object... format_args) {
        logger.warn(name + String.format(to_string.toString(), format_args));
    }

    @Override
    public void error(@NonNull final Object to_string, @Nullable final Object... format_args) {
        logger.error(name + String.format(to_string.toString(), format_args));
    }

    @Override
    public void debug(@NotNull final Object to_string, @Nullable final Object... format_args) {
        if (Properties.DEBUG) {
            if (to_string == null) {
                throw new NullPointerException(
                    "to_string is marked @NonNull but is null"); // lombok is generating before the DEBUG check. This is unintended
            }

            logger.debug(name + String.format(to_string.toString(), format_args));
        }
    }

    @NonNull
    @Override
    public Logger child(@NonNull final String name) {
        return new Logger(this.name + "/" + name);
    }
}
