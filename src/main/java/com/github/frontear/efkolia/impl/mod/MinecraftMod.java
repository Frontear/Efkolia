package com.github.frontear.efkolia.impl.mod;

import com.github.frontear.efkolia.api.mod.IMinecraftMod;
import com.github.frontear.efkolia.impl.configuration.Config;
import com.github.frontear.efkolia.impl.events.EventExecutor;
import com.github.frontear.efkolia.impl.info.Metadata;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.internal.*;
import java.io.IOException;
import java.nio.file.*;
import lombok.*;

public abstract class MinecraftMod implements IMinecraftMod {
    protected final Path directory;

    protected final Metadata metadata;
    protected final Logger logger;
    protected final EventExecutor executor;
    protected final Config config;

    @SneakyThrows(IOException.class)
    public MinecraftMod(@NonNull final String name, @NonNull final String version,
        @NonNull final String author, @Nullable final String... contributors) {
        this.directory = Paths.get(System.getProperty("user.dir"), name.toLowerCase());
        if (Files.notExists(directory)) {
            Files.createDirectory(directory);
        }

        this.metadata = new Metadata(name, version, author, contributors);
        this.logger = new Logger(this);
        this.executor = new EventExecutor(this);
        this.config = new Config(this);
    }

    @Override
    public Path getDirectory() {
        return directory;
    }

    @NotNull
    @Override
    public Metadata getMetadata() {
        return metadata;
    }

    @NotNull
    @Override
    public Logger getLogger(@NonNull final String name) {
        return logger.child(name);
    }

    @NotNull
    @Override
    public EventExecutor getExecutor() {
        return executor;
    }

    @NotNull
    @Override
    public Config getConfig() {
        return config;
    }
}
