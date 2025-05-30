package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;


import static org.objectweb.asm.Opcodes.ILOAD;

public class MethodEnterStrategyWithIntParam implements MethodEnterStrategy {

    private final String METHOD_ENTER = "methodEnterWithIntParam";
    private final String METHOD_DESCRIPTOR_OBJECT_INT_INT_ARGUMENT = "(Ljava/lang/Object;II)V";


    @Override
    public void methodEnter(MethodCallbackFactory parent, int inMethodId) {
        parent.methodVisitor().visitVarInsn(ILOAD, 1);;
        parent.methodCall(inMethodId, METHOD_ENTER, METHOD_DESCRIPTOR_OBJECT_INT_INT_ARGUMENT);
    }
}
