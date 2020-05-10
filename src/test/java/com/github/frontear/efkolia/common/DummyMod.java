package com.github.frontear.efkolia.common;

import com.github.frontear.efkolia.impl.mod.MinecraftMod;
import com.github.frontear.internal.NotNull;
import lombok.val;

public final class DummyMod extends MinecraftMod {
    private static final MinecraftMod instance;

    static {
        val temp = System.getProperty("user.dir");
        System.setProperty("user.dir", System.getProperty("java.io.tmpdir"));
        instance = new DummyMod();
        System.out.println(instance.getDirectory());
        System.setProperty("user.dir", temp);
    }

    public DummyMod() {
        super("Test", "0.11235", "Frontear");
    }

    @NotNull
    public static MinecraftMod getInstance() {
        return instance;
    }
}
