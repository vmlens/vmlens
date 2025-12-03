package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.enterstrategy.MethodEnterStrategy;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy.MethodExitStrategy;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryPreAnalyzed extends MethodCallbackFactory {


    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/PreAnalyzedCallback";


    private final MethodEnterStrategy methodEnterStrategy;
    private final MethodExitStrategy methodExitStrategy;

    public MethodCallbackFactoryPreAnalyzed(MethodEnterStrategy methodEnterStrategy,
                                            MethodExitStrategy methodExitStrategy,
                                            MethodVisitor methodVisitor) {
        super(methodVisitor);
        this.methodEnterStrategy = methodEnterStrategy;
        this.methodExitStrategy = methodExitStrategy;
    }

    public void methodEnter(int inMethodId, CalleeFactory calleeFactory) {
        calleeFactory.createCallee();
        methodEnterStrategy.methodEnter(this,inMethodId);
    }

    @Override
    public void methodExit(int inMethodId, CalleeFactory calleeFactory) {
        methodExitStrategy.methodExit(this,inMethodId,calleeFactory);
    }

    @Override
    public void methodExitWithObjectReturn(int inMethodId, CalleeFactory calleeFactory) {
        methodExitStrategy.methodExitWithObjectReturn(this,inMethodId,calleeFactory);
    }

    @Override
    public void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }

}
