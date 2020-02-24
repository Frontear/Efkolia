package com.github.frontear.efkolia.common;

import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.github.frontear.internal.NotNull;

public final class DummyMod extends MinecraftMod {
    private static MinecraftMod instance = new DummyMod();

    public DummyMod() {
        super("Test", "0.11235", "Frontear");
    }

    @NotNull
    public static MinecraftMod getInstance() {
        return instance;
    }
}
