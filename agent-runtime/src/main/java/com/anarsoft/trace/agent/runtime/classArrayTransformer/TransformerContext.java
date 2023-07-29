package com.anarsoft.trace.agent.runtime.classArrayTransformer;

import com.anarsoft.trace.agent.runtime.TransformConstants;
import com.anarsoft.trace.agent.runtime.WriteClassDescription;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class TransformerContext {
    private final byte[] classfileBuffer;
    private final String name;
    private final FilterList filterList;
    private final WriteClassDescription writeClassDescription;
    private final TransformConstants callBackStrings;
    private final HasGeneratedMethods hasGeneratedMethods;

    public TransformerContext(byte[] classfileBuffer, String name, FilterList filterList,
                              WriteClassDescription writeClassDescription, TransformConstants callBackStrings,
                              HasGeneratedMethods hasGeneratedMethods) {
        this.classfileBuffer = classfileBuffer;
        this.name = name;
        this.filterList = filterList;
        this.writeClassDescription = writeClassDescription;
        this.callBackStrings = callBackStrings;
        this.hasGeneratedMethods = hasGeneratedMethods;
    }

    public String name() {
        return name;
    }

    public FilterList filterList() {
        return filterList;
    }

    public WriteClassDescription writeClassDescription() {
        return writeClassDescription;
    }

    public TransformConstants callBackStrings() {
        return callBackStrings;
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
