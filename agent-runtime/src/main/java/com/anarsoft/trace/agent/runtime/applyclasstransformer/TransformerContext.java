package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;

public class TransformerContext {
    private final byte[] classfileBuffer;
    private final String name;
    private final WriteClassDescriptionAndWarning writeClassDescription;


    public TransformerContext(byte[] classfileBuffer, String name,
                              WriteClassDescriptionAndWarning writeClassDescription) {
        this.classfileBuffer = classfileBuffer;
        this.name = name;
        this.writeClassDescription = writeClassDescription;
    }

    public String name() {
        return name;
    }

    public WriteClassDescriptionAndWarning writeClassDescription() {
        return writeClassDescription;
    }

    public byte[] classfileBuffer() {
        return classfileBuffer;
    }
}
