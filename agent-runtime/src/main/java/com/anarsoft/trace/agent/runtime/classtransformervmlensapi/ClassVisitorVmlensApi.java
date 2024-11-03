package com.anarsoft.trace.agent.runtime.classtransformervmlensapi;

import com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassVisitorVmlensApi extends ClassVisitor {

    public static final String CALLBACK_CLASS_VMLENS_API =
            "com/vmlens/trace/agent/bootstrap/callback/VmlensApiCallback";

    public ClassVisitorVmlensApi(ClassVisitor classVisitor) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {
        if (name.startsWith("<") || (access & Opcodes.ACC_STATIC) != Opcodes.ACC_STATIC) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        } else if (name.equals("hasNext") && descriptor.equals("(Ljava/lang/Object;)Z")) {
            return new MethodVisitorHasNext(
                    super.visitMethod(access, name, descriptor, signature, exceptions), name, descriptor);
        } else if (name.equals("close") && descriptor.equals("(Ljava/lang/Object;)V")) {
            return new MethodVisitorClose(
                    super.visitMethod(access, name, descriptor, signature, exceptions), name, descriptor);
        } else {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
    }
}
