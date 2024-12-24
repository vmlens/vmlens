package com.anarsoft.trace.agent.runtime.classtransformervmlensapi;

import com.anarsoft.trace.agent.runtime.classtransformerall.ASMConstants;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassVisitorVmlensApi extends ClassVisitor {

    public ClassVisitorVmlensApi(ClassVisitor classVisitor) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {

        return new MethodVisitorReplaceStaticMethodCallTarget(
                super.visitMethod(access, name, descriptor, signature, exceptions));

    }

}
