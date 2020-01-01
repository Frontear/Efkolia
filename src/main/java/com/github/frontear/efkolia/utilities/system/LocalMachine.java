package com.github.frontear.efkolia.utilities.system;

import static com.github.frontear.efkolia.utilities.system.OperatingSystem.*;
import static com.github.frontear.efkolia.utilities.system.SystemArchitecture.*;

import com.github.frontear.internal.NotNull;
import java.lang.management.ManagementFactory;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.val;

/**
 * A utility class which allows easy access to detect operating system and architecture information.
 * The values are guaranteed to not be tampered with, and should be accurate.
 */
@UtilityClass
public class LocalMachine {
    private final OperatingSystem os;
    private final SystemArchitecture arch;

    static {
        final Function<String, Boolean> propertyCheck = property -> ManagementFactory
            .getRuntimeMXBean().getInputArguments().stream()
            .noneMatch(x -> x.startsWith(
                "-D" + property + "=")); // checks if a property explicitly changed in the jvm args.

        if (!propertyCheck.apply("os.name") && !propertyCheck.apply("os.arch")) {
            val os_name = System.getProperty("os.name");
            val os_arch = System.getProperty("os.arch");

            os = os_name.contains("Windows") ? WINDOWS : os_name.contains("Linux") ? LINUX
                : os_name.contains("OS X") ? OSX : OperatingSystem.UNKNOWN;
            arch = os_arch.equals("amd64") ? x64
                : os_arch.equals("x86") ? x86 : SystemArchitecture.UNKNOWN;
        }
        else {
            throw new UnsupportedOperationException(
                "Properties \"os.name\" or \"os.arch\" have been overwritten in the jvm arguments");
        }
    }

    /**
     * Checks if the operating system is equivalent to what is expected.
     *
     * @param system The expected operating system
     *
     * @return If the operating system is supported and equivalent.
     */
    public boolean equalsOS(@NotNull final OperatingSystem system) {
        return system.isSupported() && os == system;
    }

    /**
     * Checks if the architecture is equivalent to what is expected
     *
     * @param architecture The expected system architecture
     *
     * @return If the system architecture is supported and equivalent.
     */
    public boolean equalsArch(@NotNull final SystemArchitecture architecture) {
        return architecture.isSupported() && arch == architecture;
    }
}
