package org.frontear.impl.configuration;

import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;
import lombok.*;
import org.frontear.efkolia.configuration.*;
import org.frontear.impl.logging.Logger;
import org.frontear.internal.NotNull;

public class Config implements IConfig {
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        .enableComplexMapKeySerialization().serializeNulls().setPrettyPrinting().create();
    private final Set<IConfigurable<?>> configurables = new LinkedHashSet<>();
    private final Logger logger;
    private final Path config;

    @SneakyThrows(IOException.class)
    public Config(@NotNull final Logger logger, @NotNull final Path config) {
        this.config = config;
        this.logger = logger.child("Config");

        if (!Files.exists(config)) {
            Files.createFile(config);
        }
    }

    @Override
    public void register(@NotNull final IConfigurable<?> object) {
        val status = configurables.add(object);
        logger.debug("Registering %s, success: %b", object.getPropertyName(), status);
    }

    @Override
    public void unregister(@NotNull final IConfigurable<?> object) {
        val status = configurables.remove(object);
        logger.debug("Unregistering %s, success: %b", object.getPropertyName(), status);
    }

    @SneakyThrows(IOException.class)
    @Override
    public void load() {
        logger.debug("Loading config at %s", config);
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
        catch (FileNotFoundException | IllegalStateException e) {
            logger.debug("Config does not exist (will create)");
            this.save(); // creates the file
        }
    }

    @SneakyThrows(IOException.class)
    @Override
    public void save() {
        logger.debug("Saving config to %s", config);
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
