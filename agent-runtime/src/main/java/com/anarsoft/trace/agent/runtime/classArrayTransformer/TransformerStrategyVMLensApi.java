package com.anarsoft.trace.agent.runtime.classArrayTransformer;

import com.anarsoft.trace.agent.runtime.transformer.ClassTransformerTraceVmlensApi;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class TransformerStrategyVMLensApi implements TransformerStrategy {
    @Override
    public byte[] transform(TransformerContext context) {
        ClassWriter cw = context.createClassWriterWithFrames();
        ClassReader classReader = context.createClassReader();

        ClassVisitor classVisitor = new ClassTransformerTraceVmlensApi(cw);
        classReader.accept(classVisitor, 0);

        return cw.toByteArray();
    }
}
