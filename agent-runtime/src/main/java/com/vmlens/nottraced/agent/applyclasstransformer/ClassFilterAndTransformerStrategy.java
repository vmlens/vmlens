package com.vmlens.nottraced.agent.applyclasstransformer;

public class ClassFilterAndTransformerStrategy {
    private final ClassFilter classFilter;
    private final TransformerStrategy transformStrategy;

    public ClassFilterAndTransformerStrategy(ClassFilter classFilter, TransformerStrategy transformStrategy) {
        this.classFilter = classFilter;
        this.transformStrategy = transformStrategy;
    }

    public boolean appliesTo(String name) {
        return classFilter.take(name);
    }

    public byte[] transform(TransformerContext context) {
        return transformStrategy.transform(context);
    }

    // Visible for test
    TransformerStrategy transformStrategy() {
        return transformStrategy;
    }
}
