package com.github.frontear.efkolia.utilities.file;

import com.github.frontear.internal.NotNull;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.util.Objects;
import java.util.jar.Manifest;
import lombok.*;
import lombok.experimental.Delegate;

/**
 * A class that allows for self-introspection of a jar. It allows you to manage resources and read
 * them from the jar file. This exists because resources are tied to the ClassLoader. They can be
 * forbidden and become a pain to work with, especially in environments like Fabric (try using
 * {@link Class#getResource(String)} or such, and you'll get a null return, even for assets that
 * should exist).
 */
public final class JavaExecutable implements Closeable {
    private final Class<?> target;
    @Delegate(types = Closeable.class) private final FileSystem system;

    /**
     * Loads a java jar. Currently, this targets the executing jar. It requires a valid class in
     * order to access the correct {@link Class#getProtectionDomain()}.
     *
     * @param target The class whose jar file you wish to view.
     */
    @SneakyThrows({ IOException.class, URISyntaxException.class })
    public JavaExecutable(@NonNull final Class<?> target) {
        this.target = target;

        val path = Paths.get(target.getProtectionDomain().getCodeSource().getLocation().toURI());
        if (path.toAbsolutePath().endsWith(
            ".jar")) { // will create a ZipFileStore internally and allow us to traverse the jar
            //noinspection RedundantCast
            this.system = FileSystems.newFileSystem(path, (ClassLoader) null);
        }
        else {
            this.system = null; // likely in a development environment, in which the files are not in a jar
        }
    }

    /**
     * Attempts to find a resource within the jar file, and return an instance of
     * {@link BufferedReader} for easy reading.
     *
     * @param entry The resource to find.
     *
     * @return A {@link BufferedReader} pointing to the specific entry.
     */
    @NotNull
    @SneakyThrows(IOException.class)
    public BufferedReader getResource(@NonNull final String entry) {
        return Files.newBufferedReader(this.getEntry(entry));
    }

    /**
     * @return The jar manifest.
     */
    @NotNull
    @SneakyThrows(IOException.class)
    public Manifest getManifest() {
        return new Manifest(Files.newInputStream(this.getEntry("/META-INF/MANIFEST.MF")));
    }

    @NotNull
    @SneakyThrows(URISyntaxException.class)
    private Path getEntry(@NonNull String entry) {
        val sanitized = entry.startsWith("/") ? entry
            : "/" + entry; // FileSystem considers the jar to be the root path, so this is necessary

        if (system == null) {
            return Paths.get(Objects.requireNonNull(target.getResource(sanitized)).toURI());
        }
        else {
            return system.getPath(entry);
        }
    }
}
