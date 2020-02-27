package com.github.frontear.efkolia.impl.container;

import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import lombok.NonNull;

public final class TestParentContainer extends Container<TestParent> {
    public TestParentContainer(final @NonNull MinecraftMod mod) {
        super(mod, "com.github.frontear.efkolia.impl.container.impl");
    }
}
