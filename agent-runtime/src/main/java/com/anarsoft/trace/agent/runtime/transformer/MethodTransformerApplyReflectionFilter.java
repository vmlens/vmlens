package com.anarsoft.trace.agent.runtime.transformer;


import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodTransformerApplyReflectionFilter extends MethodVisitor  implements Opcodes {

	public MethodTransformerApplyReflectionFilter(MethodVisitor mv) {
		super(AgentClassFileTransformer.ASM_API_VERSION , mv);
		
	}
	
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc , boolean isInterface) {
		
		if (opcode == INVOKEVIRTUAL && "java/lang/Class".equals(owner) && "getDeclaredMethods".equals(name) && "()[Ljava/lang/reflect/Method;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ReflectionFilter", "getFilteredDeclaredMethods", "(Ljava/lang/Class;)[Ljava/lang/reflect/Method;");
        } else {
            mv.visitMethodInsn(opcode, owner, name, desc);
        }

    }

	
	
	
	

}
