package com.github.frontear.impl.configuration;

import com.github.frontear.internal.NotNull;
import com.google.gson.annotations.Expose;
import java.util.concurrent.ThreadLocalRandom;

class TestObject extends Configurable<TestObject> {
    @Expose
    int number = ThreadLocalRandom.current().nextInt();

    @Override
    public void load(@NotNull final TestObject value) {
        this.number = value.number;
    }
}
