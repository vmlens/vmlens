package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.enterstrategy.MethodEnterStrategy;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy.MethodExitStrategy;
import org.objectweb.asm.MethodVisitor;

public class MethodCallbackFactoryFactoryPreAnalyzed implements MethodCallbackFactoryFactory {

    private final MethodEnterStrategy methodEnterStrategy;
    private final MethodExitStrategy methodExitStrategy;

    public MethodCallbackFactoryFactoryPreAnalyzed(MethodEnterStrategy methodEnterStrategy,
                                                   MethodExitStrategy methodExitStrategy) {
        this.methodEnterStrategy = methodEnterStrategy;
        this.methodExitStrategy = methodExitStrategy;
    }

    @Override
    public MethodCallbackFactory create(MethodVisitor methodVisitor) {
        return new MethodCallbackFactoryPreAnalyzed(methodEnterStrategy,
                methodExitStrategy,
                methodVisitor);
    }

}
