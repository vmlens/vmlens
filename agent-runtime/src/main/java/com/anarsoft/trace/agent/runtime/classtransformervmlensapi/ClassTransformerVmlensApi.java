package com.anarsoft.trace.agent.runtime.classtransformervmlensapi;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ClassTransformerVmlensApi {

    public byte[] transform(byte[] classfileBuffer, String name) {

        ClassWriter classWriter = new ClassWriter(0);
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
