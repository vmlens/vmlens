package com.anarsoft.trace.agent.runtime.classArrayTransformer;

public class ClassArrayTransformer {
    private final String classNameStartsWith;
    private final TransformerStrategy transformStrategy;

    public ClassArrayTransformer(String classNameStartsWith, TransformerStrategy transformStrategy) {
        this.classNameStartsWith = classNameStartsWith;
        this.transformStrategy = transformStrategy;
    }

    public boolean appliesTo(String name) {
        return name.startsWith(classNameStartsWith);
    }

    public byte[] transform(TransformerContext context) {
        return transformStrategy.transform(context);
    }
}
