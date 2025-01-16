package com.anarsoft.trace.agent.runtime.applyclasstransformer;

public class TransformerContext {
    private final byte[] classfileBuffer;
    private final String name;


    public TransformerContext(byte[] classfileBuffer, String name) {
        this.classfileBuffer = classfileBuffer;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public byte[] classfileBuffer() {
        return classfileBuffer;
    }
}
