package com.vmlens.nottraced.agent.applyclasstransformer;

public class ClassFilterStartsWith implements ClassFilter {

    private final String expected;

    public ClassFilterStartsWith(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean take(String name) {
        return name.startsWith(expected);
    }
}
