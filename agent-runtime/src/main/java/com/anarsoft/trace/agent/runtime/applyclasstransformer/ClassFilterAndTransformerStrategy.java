package com.anarsoft.trace.agent.runtime.applyclasstransformer;

public class ClassFilterAndTransformerStrategy {
    private final String classNameStartsWith;
    private final TransformerStrategy transformStrategy;

    public ClassFilterAndTransformerStrategy(String classNameStartsWith, TransformerStrategy transformStrategy) {
        this.classNameStartsWith = classNameStartsWith;
        this.transformStrategy = transformStrategy;
    }

    public boolean appliesTo(String name) {
        return name.startsWith(classNameStartsWith);
    }

    public byte[] transform(TransformerContext context) {
        return transformStrategy.transform(context);
    }

    // Visible for test
    TransformerStrategy transformStrategy() {
        return transformStrategy;
    }
}
