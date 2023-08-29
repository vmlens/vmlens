package com.anarsoft.trace.agent.runtime.transformer;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import com.anarsoft.trace.agent.runtime.TransformConstants;

public class MethodTransformerTraceAtomicClasses  extends MethodVisitor implements Opcodes  {
	private int fieldId;
    protected final String CALLBACK_CLASS_FIELD_ACCESS;
	 public MethodTransformerTraceAtomicClasses(MethodVisitor mv, int fieldId,
			 TransformConstants callBackStrings) {
		super(AgentClassFileTransformer.ASM_API_VERSION, mv);
         this.fieldId = fieldId;
         CALLBACK_CLASS_FIELD_ACCESS = callBackStrings.CALLBACK_CLASS_FIELD_ACCESS;
	}

	public void visitCode() {
	        mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitLdcInsn(fieldId);
			mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_FIELD_ACCESS, "atomicAccess" , "(Ljava/lang/Object;I)V" , false);
	    }
}
