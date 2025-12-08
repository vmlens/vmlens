package com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;

import static org.objectweb.asm.Opcodes.ALOAD;

public class ObjectParamMethodExitStrategy extends MethodExitWithoutObjectReturnStrategy  {

    private final String METHOD_EXIT = "methodExitObjectParam";
    private final String METHOD_DESCRIPTOR = "(Ljava/lang/Object;Ljava/lang/Object;I)V";



    @Override
    public void methodExit(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory) {
        calleeFactory.createCallee();
        parent.methodVisitor().visitVarInsn(ALOAD,1);
        parent.methodCall(inMethodId, METHOD_EXIT, METHOD_DESCRIPTOR);
    }
}
