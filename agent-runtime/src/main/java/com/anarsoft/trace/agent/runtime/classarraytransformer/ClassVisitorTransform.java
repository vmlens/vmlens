package com.anarsoft.trace.agent.runtime.classarraytransformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassVisitorTransform extends ClassVisitor {

    public ClassVisitorTransform(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }


}
