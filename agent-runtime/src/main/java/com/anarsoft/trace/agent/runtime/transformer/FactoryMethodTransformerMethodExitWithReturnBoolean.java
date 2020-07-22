package com.anarsoft.trace.agent.runtime.transformer;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;

public class FactoryMethodTransformerMethodExitWithReturnBoolean implements  FactoryMethodTransformer
{

	private final String callbackClass;
	private final String callbackMethod;



	public FactoryMethodTransformerMethodExitWithReturnBoolean(String callbackClass,
			String callbackMethod) {
		super();
		this.callbackClass = callbackClass;
		this.callbackMethod = callbackMethod;
	}




	@Override
	public MethodVisitor create(MethodVisitor in) {
		// TODO Auto-generated method stub
		return new MethodTransformerTraceMethodEnterWithReturnBoolean(in);
	}



	private  final class MethodTransformerTraceMethodEnterWithReturnBoolean extends MethodVisitor implements Opcodes  {

		public MethodTransformerTraceMethodEnterWithReturnBoolean(MethodVisitor mv) {
			super( AgentClassFileTransformer.ASM_API_VERSION , mv);

		}


		private void onMethodExit() {
			mv.visitInsn(DUP);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESTATIC, callbackClass, callbackMethod , "(ZLjava/lang/Object;)V");
		}



		@Override
		public final void visitInsn(int inst) {


			switch (inst) {


			
	         case IRETURN:
	             onMethodExit();
	             break;

			default:
				break;


			}


			mv.visitInsn(inst);


		}





    }









}
