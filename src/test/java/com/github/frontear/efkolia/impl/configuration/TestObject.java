package com.github.frontear.efkolia.impl.configuration;

import com.github.frontear.efkolia.utilities.randomizer.LocalRandom;
import com.google.gson.annotations.Expose;
import lombok.NonNull;

class TestObject extends Configurable<TestObject> {
    @Expose
    int number = LocalRandom.nextInt(-100, 100);

    @Override
    public void load(@NonNull final TestObject value) {
        this.number = value.number;
    }
}
