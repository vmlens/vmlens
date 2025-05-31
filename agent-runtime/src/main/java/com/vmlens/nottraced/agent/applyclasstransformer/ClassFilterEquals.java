package com.vmlens.nottraced.agent.applyclasstransformer;

public class ClassFilterEquals implements ClassFilter {

    private final String expected;

    public ClassFilterEquals(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean take(String name) {
        return expected.equals(name);
    }
}
