package com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.classtransformervmlensapi.ClassVisitorVmlensApi;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class TransformerStrategyVmlensApi implements TransformerStrategy {

    @Override
    public byte[] transform(TransformerContext context) {
        return transform(context.classfileBuffer());
    }

    public byte[] transform(byte[] classfileBuffer) {

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        transform(classfileBuffer, classWriter);

        return classWriter.toByteArray();
    }

    // Visible for test
    public void transform(byte[] classfileBuffer,
                          ClassVisitor classWriter) {
        ClassReader readerForTransform = new ClassReader(classfileBuffer);
        ClassVisitorVmlensApi classVisitorTransform = new ClassVisitorVmlensApi(classWriter);
        readerForTransform.accept(classVisitorTransform, 0);
    }


}
