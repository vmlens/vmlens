package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;

public class MethodTransformerTraceVmlensApiOneArgumentsIntReturn  extends MethodVisitor implements Opcodes  {
	private final String name;
	private final String desc;
	
	
	private static final String CALLBACK_CLASS_VMLENS_API = "com/vmlens/trace/agent/bootstrap/callback/AgentLogCallback";
	
	
	public MethodTransformerTraceVmlensApiOneArgumentsIntReturn(MethodVisitor mv, String name, String desc) {
		super(AgentClassFileTransformer.ASM_API_VERSION,mv);
		this.name = name;
		this.desc = desc;
	}
	
	
	
	@Override
	public void visitCode() {
		
		
		super.visitCode();
		
		
		mv.visitVarInsn(ALOAD, 0);
	

		mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_VMLENS_API , name , desc );
		
		mv.visitInsn(IRETURN);
		
		
		
		
	}
	
}
