package com.github.frontear.efkolia.impl.configuration;

import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import com.github.frontear.internal.NotNull;
import com.google.gson.annotations.Expose;

class TestObject extends Configurable<TestObject> {
    @Expose
    int number = LocalRandom.nextInt(-100, 100);

    @Override
    public void load(@NotNull final TestObject value) {
        this.number = value.number;
    }
}
