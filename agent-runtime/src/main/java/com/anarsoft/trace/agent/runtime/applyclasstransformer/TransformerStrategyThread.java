package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.classtransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.transformer.ClassTransformerTraceClassThread;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class TransformerStrategyThread implements TransformerStrategy {
    @Override
    public byte[] transform(TransformerContext context) {
        ClassWriter cw = context.createClassWriter();
        ClassReader classReader = context.createClassReader();

        ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(context.name());
        classReader.accept(classVisitorCreateDesc, 0);

        ClassVisitor classVisitor = new ClassTransformerTraceClassThread(cw, context.name(),
                classVisitorCreateDesc, context.writeClassDescription());

        classReader.accept(classVisitor, 0);
        return cw.toByteArray();
    }
}
