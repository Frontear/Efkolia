package com.github.frontear.efkolia.impl.loader;

import com.github.frontear.efkolia.api.loader.ILoaderMod;

public abstract class LoaderMod implements ILoaderMod {
    public static final boolean DEBUG = Boolean.getBoolean("efkolia.debug");
}
