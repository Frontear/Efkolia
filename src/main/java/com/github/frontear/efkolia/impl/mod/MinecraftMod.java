package com.github.frontear.efkolia.impl.mod;

import com.github.frontear.efkolia.api.mod.IMinecraftMod;
import com.github.frontear.efkolia.impl.configuration.Config;
import com.github.frontear.efkolia.impl.info.Metadata;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.internal.*;
import java.nio.file.Paths;

public abstract class MinecraftMod implements IMinecraftMod {
    private final Metadata metadata;
    private final Logger logger;
    private final Config config;

    public MinecraftMod(@NotNull final String name, @NotNull final String version,
        @NotNull final String author, @Nullable final String... contributors) {
        this.metadata = new Metadata(name, version, author, contributors);
        this.logger = new Logger(name);
        this.config = new Config(logger,
            Paths.get(System.getProperty("user.dir"), name.toLowerCase() + ".json"));
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
    public Config getConfig() {
        return config;
    }
}
