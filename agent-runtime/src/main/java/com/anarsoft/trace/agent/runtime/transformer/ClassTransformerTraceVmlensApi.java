package com.anarsoft.trace.agent.runtime.transformer;


import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;



public class ClassTransformerTraceVmlensApi  extends ClassVisitor {

	public ClassTransformerTraceVmlensApi(ClassVisitor cv) {
		super(AgentClassFileTransformer.ASM_API_VERSION , cv);
	}

	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
        if (name.startsWith("<") || (access & Opcodes.ACC_STATIC) != Opcodes.ACC_STATIC) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        } else if (name.equals("hasNext") && desc.equals("(Ljava/lang/Object;)Z")) {
            return new MethodTransformerTraceVmlensApiOneArgumentsIntReturn(super.visitMethod(access, name, desc, signature, exceptions), name, desc);
        } else if (name.equals("close") && desc.equals("(Ljava/lang/Object;)V")) {
            return new MethodTransformerTraceVmlensApiOneArgumentNoReturn(super.visitMethod(access, name, desc, signature, exceptions), name, desc);
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
	}
}
