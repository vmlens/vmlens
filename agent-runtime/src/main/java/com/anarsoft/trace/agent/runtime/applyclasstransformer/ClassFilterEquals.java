package com.anarsoft.trace.agent.runtime.applyclasstransformer;

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
