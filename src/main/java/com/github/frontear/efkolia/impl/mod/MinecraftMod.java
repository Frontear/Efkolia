package com.github.frontear.efkolia.impl.mod;

import com.github.frontear.efkolia.api.configuration.IConfig;
import com.github.frontear.efkolia.api.info.IMetadata;
import com.github.frontear.efkolia.api.logging.ILogger;
import com.github.frontear.efkolia.api.mod.IMinecraftMod;
import com.github.frontear.internal.NotNull;

public abstract class MinecraftMod implements IMinecraftMod {
    private final IMetadata metadata;
    private final IConfig config;
    private final ILogger logger;

    public MinecraftMod(@NotNull final IMetadata metadata, @NotNull final IConfig config,
        @NotNull final ILogger logger) {
        this.metadata = metadata;
        this.config = config;
        this.logger = logger;
    }

    @NotNull
    @Override
    public IMetadata getMetadata() {
        return metadata;
    }

    @NotNull
    @Override
    public IConfig getConfig() {
        return config;
    }

    @NotNull
    @Override
    public ILogger getLogger(@NotNull final String name) {
        return logger.child(name);
    }
}
