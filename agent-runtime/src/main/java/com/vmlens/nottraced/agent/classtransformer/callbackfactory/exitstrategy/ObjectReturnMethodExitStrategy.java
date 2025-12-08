package com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;
import static org.objectweb.asm.Opcodes.DUP;

public class ObjectReturnMethodExitStrategy implements MethodExitStrategy {

    private final String METHOD_EXIT_OBJECT_RETURN = "methodExitObjectReturn";
    private final String METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;Ljava/lang/Object;I)V";

    @Override
    public void methodExitWithObjectReturn(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory) {
        parent.methodVisitor().visitInsn(DUP);
        calleeFactory.createCallee();
        parent.methodCall(inMethodId, METHOD_EXIT_OBJECT_RETURN, METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT);
    }

    /*
        this is for athrow see MethodEnterExitTransform,
        not quite sure if this actual can happen
        and what to do
     */
    @Override
    public void methodExit(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory) {

    }
}
