package com.vmlens.nottraced.agent.applyclasstransformer;

public class ClassFilterContains implements ClassFilter {

    private final String expected;

    public ClassFilterContains(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean take(String name) {
        return expected.contains(name);
    }

}
