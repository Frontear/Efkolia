package com.github.frontear.efkolia.impl.configuration;

import com.github.frontear.efkolia.api.configuration.*;
import com.github.frontear.efkolia.impl.logging.Logger;
import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;
import lombok.*;

public final class Config implements IConfig {
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        .enableComplexMapKeySerialization().serializeNulls().setPrettyPrinting().create();
    private final Set<IConfigurable<?>> configurables = new LinkedHashSet<>();
    private final Logger logger;
    private final Path config;

    @SneakyThrows(IOException.class)
    public Config(@NonNull final MinecraftMod mod, @NonNull final String parent) {
        this.logger = mod.getLogger("Config");
        this.config = Paths.get(parent, mod.getMetadata().getName().toLowerCase() + ".json");

        if (!Files.exists(config)) {
            Files.createFile(config);
        }
    }

    @Override
    public void register(@NonNull final IConfigurable<?> object) {
        val status = configurables.add(object);

        if (status) {
            logger.debug("Registering %s", object.getPropertyName());
        }
    }

    @Override
    public void unregister(@NonNull final IConfigurable<?> object) {
        val status = configurables.remove(object);

        if (status) {
            logger.debug("Unregistering %s", object.getPropertyName());
        }
    }

    @Override
    @SneakyThrows(IOException.class)
    public void load() {
        logger.debug("Loading the config from %s", config);
        try (val reader = Files.newBufferedReader(config)) {
            logger.debug("Parsing via JsonParser");
            val config = new JsonParser().parse(reader).getAsJsonObject();

            for (val obj : configurables) {
                val name = obj.getPropertyName();

                logger.debug("Lookup %s in json", name);
                if (config.has(name)) {
                    logger.debug("Loading %s", name);
                    obj.load(gson.fromJson(config.get(name), (Type) obj.getClass()));
                }
                else {
                    logger.debug("%s not found (will be added once saved)", name);
                }
            }
        }
        catch (final FileNotFoundException | IllegalStateException e) {
            logger.debug("Config does not exist (will create)");
            this.save(); // creates the file
        }
    }

    @Override
    @SneakyThrows(IOException.class)
    public void save() {
        logger.debug("Saving the config to %s", config);
        try (val writer = Files.newBufferedWriter(config)) {
            val config = new JsonObject();

            for (val obj : configurables) {
                val name = obj.getPropertyName();

                logger.debug("Saving %s", name);
                config.add(name, gson.toJsonTree(obj));
            }

            logger.debug("Writing json");
            gson.toJson(config, writer);
        }
    }
}
