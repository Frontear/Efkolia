package com.github.frontear.efkolia.utilities.system;

/**
 * An enumeration for the possible system architecture's a local machine can be. These are detected
 * through the "os.arch" property, and handled by {@link LocalMachine}.
 */
@Deprecated
public enum SystemArchitecture {
    x64,
    x86,
    UNKNOWN;

    /**
     * @return If the property has support, basically if it isn't {@link
     * SystemArchitecture#UNKNOWN}.
     */
    public boolean isSupported() {
        return this != UNKNOWN;
    }
}
