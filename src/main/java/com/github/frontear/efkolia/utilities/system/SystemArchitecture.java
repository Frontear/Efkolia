package com.github.frontear.efkolia.utilities.system;

/**
 * An enumeration for the possible system architecture's a local machine can be. These are detected
 * through the "os.arch" property, and handled by {@link LocalMachine}.
 */
public enum SystemArchitecture {
    x64,
    x86,
    UNKNOWN;

    /**
     * @return True if the property is supported, or basically isn't {@link
     * SystemArchitecture#UNKNOWN}
     */
    public boolean isSupported() {
        return this != UNKNOWN;
    }
}
