package com.anarsoft.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.FieldAccessCallback;
import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;

public class GeneratedMethodExample {

	/*
	 * 	private void createCallbackMethod() {
		{
			MethodVisitor mv = cv.visitMethod(ACC_SYNTHETIC | ACC_PUBLIC, "_pAnarsoft_field_access", "(III)V", null,
					null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, className, "_pAnarsoft_",
					"Lcom/vmlens/trace/agent/bootstrap/callback/state/ObjectState;");
			mv.visitVarInsn(ILOAD, 1);
			mv.visitVarInsn(ILOAD, 2);
			mv.visitVarInsn(ILOAD, 3);
			mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_FIELD_ACCESS, "field_access_from_generated_method",
					"(Ljava/lang/Object;Ljava/lang/Object;III)V");
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitInsn(RETURN);
			Label l2 = new Label();
			mv.visitLabel(l2);
			// mv.visitLocalVariable("this", className, null, l0, l2, 0);
			mv.visitLocalVariable("fieldId", "I", null, l0, l2, 1);
			mv.visitLocalVariable("methodId", "I", null, l0, l2, 2);
			mv.visitLocalVariable("callBackId", "I", null, l0, l2, 3);

	 */
	
	
	private volatile Object _pAnarsoft_;
	private static Long _pAnarsoft_offset;
	
//	static {
//		_pAnarsoft_offset = UpdateObjectState.getFieldOffset(GeneratedMethodExample.class);
//	}
	
	
	/*
	 *  public static void field_access_from_generated_method(Object orig,long offset , int fieldId, int methodId , int callbackId)
	 */
	
	public void _pAnarsoft_field_access(int fieldId, int methodId , int callbackId)
	{
		_pAnarsoft_offset = 	FieldAccessCallback.field_access_from_generated_method(this, _pAnarsoft_offset, fieldId, methodId, callbackId);
	}
	
	
	
}
