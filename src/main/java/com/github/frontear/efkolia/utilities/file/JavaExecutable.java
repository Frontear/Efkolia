package com.github.frontear.efkolia.utilities.file;

import com.github.frontear.internal.NotNull;
import java.io.*;
import java.util.jar.Manifest;
import java.util.zip.ZipFile;
import lombok.*;

/**
 * A class that allows for self-introspection of the executing jar. It allows you to manage
 * resources and read them from the jar file. This exists because resources are tied to the
 * ClassLoader. They can be forbidden and become a pain to work with, especially in environments
 * like Fabric (try using {@link Class#getResource(String)}) or such and you'll get a null return,
 * even for assets that should exist). As such, this is a necessity. This will only work if the
 * program is executed from a jar file. This means it will almost certainly fail during production
 * testing, unless your testing environment allows for compilation into a jar, then execution.
 */
public final class JavaExecutable {
    private final ZipFile file;

    /**
     * Loads a java jar. Currently, this targets the executing jar. It requires a valid class in
     * order to access the correct {@link Class#getProtectionDomain()}.
     *
     * @param target The class whose jar file you wish to view.
     */
    @SneakyThrows(IOException.class)
    public JavaExecutable(@NonNull final Class<?> target) {
        this.file = new ZipFile(
            new File(target.getProtectionDomain().getCodeSource().getLocation().getPath()));
    }

    /**
     * Attempts to find a resource within the jar file, and return an instance of {@link
     * BufferedReader} for easy reading.
     *
     * @param entry The resource to find.
     *
     * @return A {@link BufferedReader} pointing to the specific entry.
     */
    @NotNull
    @SneakyThrows(IOException.class)
    public BufferedReader getResource(@NonNull final String entry) {
        return new BufferedReader(new InputStreamReader(file.getInputStream(file.getEntry(entry))));
    }

    /**
     * @return The jar manifest.
     */
    @NotNull
    @SneakyThrows(IOException.class)
    public Manifest getManifest() {
        val manifest = file.getInputStream(file.getEntry("META-INF/MANIFEST.MF"));
        return new Manifest(manifest);
    }
}
