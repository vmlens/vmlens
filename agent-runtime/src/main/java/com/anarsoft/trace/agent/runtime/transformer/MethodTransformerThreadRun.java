package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.MethodVisitor;

import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;

import static com.anarsoft.trace.agent.runtime.TransformConstants.CALLBACK_CLASS_METHOD;

public class MethodTransformerThreadRun extends MethodTransformerAbstract {

    public MethodTransformerThreadRun(MethodVisitor mv, int access, String desc, String name, String className,
                                      String superClassName, int tryCatchBlockCount, MethodDescriptionBuilder methodDescriptionBuilder, boolean dottyProblematic, boolean useExpandedFrames) {
        super(mv, access, desc, name, className, superClassName, tryCatchBlockCount, methodDescriptionBuilder, dottyProblematic, useExpandedFrames);

    }

    protected void onMethodReturn() {

        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_METHOD, "methodExitThreadRun", "(I)V");
	}

	protected void onMethodEscapedException() {
		onMethodReturn();
	}

	protected void onMethodEnter() {

        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_METHOD, "methodEnterThreadRun", "(I)V");

	}

	protected int getMethodId() {
		return this.methodDescriptionBuilder.getId();
	}

	protected void onMonitorEnter() {

		this.mv.visitInsn(MONITORENTER);

	}

	protected void onMonitorExit() {

	}

	public void visitMethodInsnInChild(int opcode, String owner, String name, String desc, boolean isInterface) {
		mv.visitMethodInsn(opcode, owner, name, desc, isInterface);
	}

	protected void onArrayStore() {

	}

	protected void onArrayLoad() {

	}

}
