package com.github.frontear.efkolia.utilities.inspect;

import com.github.frontear.internal.NotNull;

public class TestClass extends TestSuperclass {
    private String s = "hello";

    @NotNull
    private String s() {
        return "hey";
    }
}
