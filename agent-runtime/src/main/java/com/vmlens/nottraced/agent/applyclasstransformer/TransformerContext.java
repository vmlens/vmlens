package com.vmlens.nottraced.agent.applyclasstransformer;

public class TransformerContext {
    private final byte[] classfileBuffer;
    private final String name;
    private final boolean isInReTransform;

    public TransformerContext(byte[] classfileBuffer,
                              String name,
                              boolean isInReTransform) {
        this.classfileBuffer = classfileBuffer;
        this.name = name;
        this.isInReTransform = isInReTransform;
    }

    public String name() {
        return name;
    }

    public byte[] classfileBuffer() {
        return classfileBuffer;
    }

    public boolean isInReTransform() {
        return isInReTransform;
    }
}
