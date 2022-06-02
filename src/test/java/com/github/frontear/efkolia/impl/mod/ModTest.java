package com.github.frontear.efkolia.impl.mod;

import com.github.frontear.internal.NotNull;
import lombok.val;

public final class ModTest extends MinecraftMod {
    private static final MinecraftMod instance;

    static {
        val temp = System.getProperty("user.dir");
        System.setProperty("user.dir", System.getProperty("java.io.tmpdir"));
        instance = new ModTest();
        System.out.println(instance.getDirectory());
        System.setProperty("user.dir", temp);
    }

    public ModTest() {
        super("Test", "0.11235", "Frontear");
    }

    @NotNull
    public static MinecraftMod getInstance() {
        return instance;
    }
}
