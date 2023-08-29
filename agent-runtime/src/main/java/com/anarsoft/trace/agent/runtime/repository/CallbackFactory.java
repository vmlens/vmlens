package com.anarsoft.trace.agent.runtime.repository;

import com.vmlens.trace.agent.bootstrap.FieldIdAndTyp;
import com.vmlens.trace.agent.bootstrap.FieldTyp;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class CallbackFactory implements Opcodes {
	private static final String CALLBACK_CLASS_FIELD_ACCESS = "com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback";
	
	private static final String CALLBACK_CLASS_UNDEFINED_FIELD_ACCESS = "com/vmlens/trace/agent/bootstrap/callback/UndefinedFieldAccessCallback";
	
	private static final String  CALLBACK_METHOD_GENERATED = "_pAnarsoft_field_access";
	private static final String  CALLBACK_DESC_GENERATED   = "(III)V";
	
	private static final String  CALLBACK_METHOD_STATIC = "field_access_static";
	private static final String  CALLBACK_DESC_STATIC   = "(III)V";
	private static final String  CALLBACK_DESC_STATIC_UNDEFINED   = "(II)V";
	
	
	
	private static final String  CALLBACK_METHOD_DEFAULT = "field_access";
	private static final String  CALLBACK_DESC_DEFAULT   = "(Ljava/lang/Object;III)V";
	private static final String  CALLBACK_DESC_UNDEFINED   = "(Ljava/lang/Object;II)V";
	

	public void createStaticCallbackForFieldWrite(String owner, String name, MethodVisitor mv, FieldIdAndTyp typAndId) {
		generateCallbackStatic( owner,  name,  mv,  typAndId,
				true);
		
	}

	public void createStaticCallbackForFieldRead(String owner, String name, MethodVisitor mv, FieldIdAndTyp typAndId) {
		generateCallbackStatic( owner,  name,  mv,  typAndId,
				false);
	}

	public void createCallbackForFieldWrite(String owner, String fieldName, MethodVisitor mv, FieldIdAndTyp typAndId,
			boolean hasGeneratedMethods) {
		generateCallback( owner,  fieldName,  mv,  typAndId,
				 hasGeneratedMethods,true);
	}

		public void createCallbackForFieldRead(String owner, String name, MethodVisitor mv, FieldIdAndTyp typAndId,
				boolean hasGeneratedMethods) {
			generateCallback( owner,  name,  mv,  typAndId,
					 hasGeneratedMethods,false);
		}

    private void generateCallback(String owner, String fieldName, MethodVisitor mv, FieldIdAndTyp typAndId,
                                  boolean hasGeneratedMethods, boolean isWrite) {

        if (typAndId.fieldTyp == FieldTyp.UNDEFINED) {
            String methodName = CALLBACK_METHOD_DEFAULT;
            if (isWrite) {
                methodName = methodName + "_write";
            } else {
                methodName = methodName + "_read";
            }
            mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_UNDEFINED_FIELD_ACCESS, methodName, CALLBACK_DESC_UNDEFINED, false);

        } else {
            int callbackId = CreateCallbackId.create(isWrite, typAndId.fieldTyp);

            mv.visitLdcInsn(Integer.valueOf(callbackId));


            if (hasGeneratedMethods) {
                mv.visitMethodInsn(INVOKEVIRTUAL, owner, CALLBACK_METHOD_GENERATED, CALLBACK_DESC_GENERATED, false);

            } else {
                mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_FIELD_ACCESS, CALLBACK_METHOD_DEFAULT, CALLBACK_DESC_DEFAULT, false);
            }
        }
		}

		private  void generateCallbackStatic(String owner, String fieldName, MethodVisitor mv, FieldIdAndTyp typAndId,
				boolean isWrite)
		{

            if (typAndId.fieldTyp == FieldTyp.UNDEFINED) {
                String methodName = CALLBACK_METHOD_STATIC;


                if (isWrite) {
                    methodName = methodName + "_write";
                } else {
                    methodName = methodName + "_read";
                }

                mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_UNDEFINED_FIELD_ACCESS, methodName, CALLBACK_DESC_STATIC_UNDEFINED, false);
            } else {


                int callbackId = CreateCallbackId.create(isWrite, typAndId.fieldTyp);
                mv.visitLdcInsn(Integer.valueOf(callbackId));
                mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_FIELD_ACCESS, CALLBACK_METHOD_STATIC, CALLBACK_DESC_STATIC, false);
            }
		}
}
