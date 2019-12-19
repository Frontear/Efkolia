package com.github.frontear.impl.configuration;

import com.github.frontear.efkolia.configuration.IConfigurable;
import com.github.frontear.internal.NotNull;

public abstract class Configurable<E extends IConfigurable<E>> implements IConfigurable<E> {
    @NotNull
    @Override
    public String getPropertyName() {
        return this.getClass().getSimpleName();
    }
}
