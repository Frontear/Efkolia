package com.github.frontear.efkolia.utilities.system;

/**
 * An enumeration for the various operating systems that are supported. These are detected through
 * the "os.name" property from Java, and handled by {@link LocalMachine}.
 */
public enum OperatingSystem {
    WINDOWS,
    LINUX,
    OSX,
    UNKNOWN;

    /**
     * @return If the property has support, basically if it isn't {@link OperatingSystem#UNKNOWN}.
     */
    public boolean isSupported() {
        return this != UNKNOWN;
    }
}
