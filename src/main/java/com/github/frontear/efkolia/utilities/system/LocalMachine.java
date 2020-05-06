package com.github.frontear.efkolia.utilities.system;

import static com.github.frontear.efkolia.utilities.system.OperatingSystem.*;
import static com.github.frontear.efkolia.utilities.system.SystemArchitecture.*;

import com.github.frontear.internal.NotNull;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.nio.file.*;
import java.util.function.Function;
import lombok.*;
import lombok.experimental.UtilityClass;

/**
 * A utility class which allows easy access to detect operating system and architecture information
 * as well as specific procedure information specialized for each OS.
 */
@Deprecated
@UtilityClass
public class LocalMachine {
    private final OperatingSystem os;
    private final SystemArchitecture arch;

    static {
        final Function<String, Boolean> propertyCheck = property -> ManagementFactory
            .getRuntimeMXBean().getInputArguments().stream()
            .noneMatch(x -> x.startsWith(
                "-D" + property + "=")); // checks if a property explicitly changed in the jvm args.

        if (propertyCheck.apply("os.name") && propertyCheck.apply("os.arch")) {
            val os_name = System.getProperty("os.name");
            val os_arch = System.getProperty("os.arch");

            os = os_name.contains("Windows") ? WINDOWS : os_name.contains("Linux") ? LINUX
                : os_name.contains("OS X") ? OSX : OperatingSystem.UNKNOWN;
            arch = os_arch.equals("amd64") ? x64
                : os_arch.equals("x86") ? x86 : SystemArchitecture.UNKNOWN;
        }
        else {
            throw new UnsupportedOperationException(
                "Properties \"os.name\" and/or \"os.arch\" have been overwritten in the jvm arguments");
        }
    }

    /**
     * Grabs a {@link Path} based on a file url. It will automatically format path separators based
     * on the operating system.
     *
     * @param path The path to the file. This <b>must</b> use unix style path separators ('/'), they
     *             will be internally changed to reflect the correct system.
     *
     * @return An instance of {@link Path} pointing to the file.
     */
    @NotNull
    @SneakyThrows(URISyntaxException.class)
    public Path getFile(@NonNull final String path) {
        val path_url = equalsOS(WINDOWS) ? path.replace('/', '\\') : path;

        return Paths.get(new URI(path));
    }

    /**
     * Attempts to grab the local clipboard data, and return it as a string.
     *
     * @return Clipboard data as a {@link String}.
     */
    @NotNull
    @SneakyThrows({ UnsupportedFlavorException.class, IOException.class })
    public String getClipboard() {
        val toolkit = Toolkit.getDefaultToolkit();

        return (String) toolkit.getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    /**
     * Sets the local clipboard to a custom string, discarding any old data.
     *
     * @param content The new clipboard text.
     */
    public void setClipboard(@NonNull final String content) {
        val toolkit = Toolkit.getDefaultToolkit();
        if (!content.isEmpty()) {
            val string = new StringSelection(content);
            toolkit.getSystemClipboard().setContents(string, string);
        }
    }

    /**
     * Checks if the operating system is equivalent to what is expected.
     *
     * @param system The expected operating system.
     *
     * @return If the operating system has support and is equivalent to what was determined.
     */
    public boolean equalsOS(@NonNull final OperatingSystem system) {
        return system.isSupported() && os == system;
    }

    /**
     * Checks if the architecture is equivalent to what is expected.
     *
     * @param architecture The expected system architecture.
     *
     * @return If the system architecture has support and is equivalent to what was determined.
     */
    public boolean equalsArch(@NonNull final SystemArchitecture architecture) {
        return architecture.isSupported()
            && arch == architecture;
    }
}
