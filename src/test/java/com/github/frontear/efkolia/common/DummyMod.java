package com.github.frontear.efkolia.common;

import com.github.frontear.efkolia.impl.mod.MinecraftMod;

public final class DummyMod extends MinecraftMod {
    private static MinecraftMod instance = new DummyMod();

    public DummyMod() {
        super("Test", "0.11235", "Frontear");
    }

    public static MinecraftMod getInstance() {
        return instance;
    }
}
