package org.frontear.impl.configuration;

import com.google.gson.annotations.Expose;
import java.util.concurrent.ThreadLocalRandom;
import org.frontear.internal.NotNull;

public class TestObject extends Configurable<TestObject> {
    @Expose
    int number = ThreadLocalRandom.current().nextInt();

    @Override
    public void load(@NotNull final TestObject value) {
        this.number = value.number;
    }
}
