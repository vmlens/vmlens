package com.anarsoft.trace.agent.runtime.transformer;


import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodTransformerThreadJoin  extends MethodVisitor implements Opcodes {

	

	public MethodTransformerThreadJoin(MethodVisitor mv) {
		super(AgentClassFileTransformer.ASM_API_VERSION   , mv);
	}

	@Override
	public final void visitCode() {
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallize/ParallizeCallback", "beforeThreadJoin",  "(Ljava/lang/Thread;)V" );

	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, boolean isInterface) {
				
		if(  MethodTransformer.isThreadActiveCall(opcode,owner,name,desc) )
	    {
			MethodTransformer.onThreadActiveCall( mv ,opcode,owner,name,desc);
	    }
		else
		{
			
				mv.visitMethodInsn(opcode, owner, name, desc);
			
			
		}

		
		}

	
	@Override
	public final void visitInsn(int inst) {

	if(inst  == RETURN )
	{

		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(LLOAD, 1);
		this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallize/ParallizeCallback", "afterThreadJoin",  "(Ljava/lang/Thread;J)V" );


	}
	mv.visitInsn(inst);
	}
	
	
	
	
}

