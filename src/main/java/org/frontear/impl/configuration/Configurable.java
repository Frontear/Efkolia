package org.frontear.impl.configuration;

import org.frontear.efkolia.configuration.IConfigurable;
import org.frontear.internal.NotNull;

public abstract class Configurable<E extends IConfigurable<E>> implements IConfigurable<E> {
    @NotNull
    @Override
    public String getPropertyName() {
        return this.getClass().getSimpleName();
    }
}
