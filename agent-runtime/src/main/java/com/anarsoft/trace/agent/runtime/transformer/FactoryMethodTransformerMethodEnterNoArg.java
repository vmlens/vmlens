package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;

public class FactoryMethodTransformerMethodEnterNoArg implements  FactoryMethodTransformer
{

	private final String callbackClass;
	private final String callbackMethod;



	public FactoryMethodTransformerMethodEnterNoArg(String callbackClass,
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
			super( AgentClassFileTransformer.ASM_API_VERSION  , mv);

		}


		private void onMethodEnter() {
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESTATIC, callbackClass, callbackMethod , "(Ljava/lang/Object;)V");
		}



		@Override
		 public final void visitCode() {
		        mv.visitCode();
		        onMethodEnter();
		               
		    }





    }









}
