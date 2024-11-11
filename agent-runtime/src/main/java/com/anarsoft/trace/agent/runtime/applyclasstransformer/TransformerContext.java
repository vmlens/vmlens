package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;

public class TransformerContext {
    private final byte[] classfileBuffer;
    private final String name;
    private final WriteClassDescription writeClassDescription;


    public TransformerContext(byte[] classfileBuffer, String name,
                              WriteClassDescription writeClassDescription) {
        this.classfileBuffer = classfileBuffer;
        this.name = name;
        this.writeClassDescription = writeClassDescription;
    }

    public String name() {
        return name;
    }

    public WriteClassDescription writeClassDescription() {
        return writeClassDescription;
    }

    public byte[] classfileBuffer() {
        return classfileBuffer;
    }
}
