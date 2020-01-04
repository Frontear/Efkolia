package com.github.frontear.efkolia.impl.mod;

import com.github.frontear.efkolia.api.mod.IMinecraftMod;
import com.github.frontear.efkolia.impl.configuration.Config;
import com.github.frontear.efkolia.impl.events.EventExecutor;
import com.github.frontear.efkolia.impl.info.Metadata;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.internal.*;

public abstract class MinecraftMod implements IMinecraftMod {
    private final Metadata metadata;
    private final Logger logger;
    private final EventExecutor executor;
    private final Config config;

    public MinecraftMod(@NotNull final String name, @NotNull final String version,
        @NotNull final String author, @Nullable final String... contributors) {
        this.metadata = new Metadata(name, version, author, contributors);
        this.logger = new Logger(name);
        this.executor = new EventExecutor(this);
        this.config = new Config(this, System.getProperty("user.dir"));
    }

    @NotNull
    @Override
    public Metadata getMetadata() {
        return metadata;
    }

    @NotNull
    @Override
    public Logger getLogger(@NotNull final String name) {
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
