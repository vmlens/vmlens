package com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.DUP;

public class ObjectStringParamObjectReturnMethodExitStrategy  implements MethodExitStrategy {

    public static final String METHOD_EXIT_RETURN_AND_PARAM = "methodObjectStringParamObjectReturn";
    public static final String METHOD_DESCRIPTOR_RETURN_AND_PARAM = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I)V";

    @Override
    public void methodExitWithObjectReturn(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory) {
        parent.methodVisitor().visitInsn(DUP);
        parent.methodVisitor().visitVarInsn(ALOAD,0);
        parent.methodVisitor().visitVarInsn(ALOAD,1);
        parent.methodCall(inMethodId, METHOD_EXIT_RETURN_AND_PARAM, METHOD_DESCRIPTOR_RETURN_AND_PARAM);
    }

    @Override
    public void methodExit(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory) {

    }
}
