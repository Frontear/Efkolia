package com.github.frontear.efkolia.impl.configuration;

import com.github.frontear.efkolia.api.configuration.IConfigurable;
import com.github.frontear.internal.NotNull;

public abstract class Configurable<E extends IConfigurable<E>> implements IConfigurable<E> {
    @NotNull
    @Override
    public String getPropertyName() {
        return this.getClass().getSimpleName();
    }
}
