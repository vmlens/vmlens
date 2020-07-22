package com.anarsoft.trace.agent.runtime.transformer;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;

public class FactoryMethodTransformerMethodExit implements  FactoryMethodTransformer
{

	private final String callbackClass;
	private final String callbackMethod;



	public FactoryMethodTransformerMethodExit(String callbackClass,
			String callbackMethod) {
		super();
		this.callbackClass = callbackClass;
		this.callbackMethod = callbackMethod;
	}




	@Override
	public MethodVisitor create(MethodVisitor in) {
		// TODO Auto-generated method stub
		return new MethodTransformerTraceMethodEnter(in);
	}



	private  final class MethodTransformerTraceMethodEnter extends MethodVisitor implements Opcodes  {

		public MethodTransformerTraceMethodEnter(MethodVisitor mv) {
			super( AgentClassFileTransformer.ASM_API_VERSION , mv);

		}


		private void onMethodExit(int id) {
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESTATIC, callbackClass, callbackMethod , "(Ljava/lang/Object;)V");
		}



		@Override
		public final void visitInsn(int inst) {


			switch (inst) {


			 case RETURN:
	         case IRETURN:
	         case FRETURN:
	         case ARETURN:
	         case LRETURN:
	         case DRETURN:
	         case ATHROW:
	             onMethodExit(inst);
	             break;

			default:
				break;


			}


			mv.visitInsn(inst);


		}





    }









}
