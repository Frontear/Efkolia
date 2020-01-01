package com.github.frontear.efkolia.impl.logging;

import static org.apache.logging.log4j.Level.*;

import com.github.frontear.efkolia.api.logging.ILogger;
import com.github.frontear.internal.*;
import java.util.function.BooleanSupplier;
import lombok.val;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender.Target;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;

public final class Logger implements ILogger {
    private static final LoggerContext context;

    static {
        val builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        {
            builder.setStatusLevel(Level.WARN);

            val console = builder.newAppender("SysOut", "Console");
            console.addAttribute("target", Target.SYSTEM_OUT);
            console.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "[%d{HH:mm:ss}] [%t/%level] [%logger]: %msg%n"));
            builder.add(console);

            val file = builder.newAppender("File", "RollingRandomAccessFile");
            file.addAttribute("fileName", "logs/latest.log")
                .addAttribute("filePattern", "logs/%d{yyyy-MM-dd}.log.gz");
            file.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "[%d{HH:mm:ss}] [%t/%level] [%logger]: %msg%n"));
            file.add(builder.newLayout("Policies")
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy"))
                .addComponent(builder.newComponent("OnStartupTriggeringPolicy")));
            builder.add(file);

            val root = builder.newRootLogger(DEBUG);
            root.add(builder.newAppenderRef("SysOut"))
                .add(builder.newAppenderRef("File").addAttribute("level", ALL));
            builder.add(root);
        }

        LogManager.shutdown(); // destroys any previous configuration
        context = Configurator.initialize(builder.build());
    }

    private final String name;
    private final BooleanSupplier debug;
    private final org.apache.logging.log4j.Logger logger;

    public Logger(@NotNull final String name, @NotNull final BooleanSupplier debug) {
        this.name = name;
        this.debug = debug;
        this.logger = context.getLogger(name);
    }

    @Override
    public void info(@NotNull final Object to_string, @Nullable final Object... format_args) {
        logger.info(String.format(to_string.toString(), format_args));
    }

    @Override
    public void warn(@NotNull final Object to_string, @Nullable final Object... format_args) {
        logger.warn(String.format(to_string.toString(), format_args));
    }

    @Override
    public void error(@NotNull final Object to_string, @Nullable final Object... format_args) {
        logger.error(String.format(to_string.toString(), format_args));
    }

    @Override
    public void debug(@NotNull final Object to_string, @Nullable final Object... format_args) {
        if (debug.getAsBoolean()) {
            logger.debug(String.format(to_string.toString(), format_args));
        }
    }

    @NotNull
    @Override
    public Logger child(@NotNull final String name) {
        return new Logger(this.name + "/" + name, this.debug);
    }
}
