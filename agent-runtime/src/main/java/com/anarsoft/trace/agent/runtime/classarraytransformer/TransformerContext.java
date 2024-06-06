package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class TransformerContext {
    private final byte[] classfileBuffer;
    private final String name;
    private final WriteClassDescription writeClassDescription;

    private final HasGeneratedMethods hasGeneratedMethods;

    public TransformerContext(byte[] classfileBuffer, String name,
                              WriteClassDescription writeClassDescription,
                              HasGeneratedMethods hasGeneratedMethods) {
        this.classfileBuffer = classfileBuffer;
        this.name = name;
        this.writeClassDescription = writeClassDescription;
        this.hasGeneratedMethods = hasGeneratedMethods;
    }

    public String name() {
        return name;
    }

    public WriteClassDescription writeClassDescription() {
        return writeClassDescription;
    }


    public ClassWriter createClassWriter() {
        return new ClassWriter(ClassWriter.COMPUTE_MAXS);
    }

    public ClassWriter createClassWriterWithFrames() {
        return new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    }

    public ClassReader createClassReader() {
        return new ClassReader(classfileBuffer);
    }

    public HasGeneratedMethods hasGeneratedMethods() {
        return hasGeneratedMethods;
    }
}
