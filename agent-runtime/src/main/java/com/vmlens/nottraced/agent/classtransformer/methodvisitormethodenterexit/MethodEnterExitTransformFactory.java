package com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.FactoryContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.TransformFactory;
import org.objectweb.asm.MethodVisitor;

public class MethodEnterExitTransformFactory implements TransformFactory {

    // Fixme remove
    private final int tryCatchBlockCount;
    private final MethodCallbackFactoryFactory factoryFactory;

    public MethodEnterExitTransformFactory(int tryCatchBlockCount,
                                           MethodCallbackFactoryFactory factoryFactory) {
        this.tryCatchBlockCount = tryCatchBlockCount;
        this.factoryFactory = factoryFactory;
    }

    @Override
    public MethodVisitor create(FactoryContext factoryContext, MethodVisitor previous) {
        return new MethodEnterExitTransform(previous,factoryContext,factoryFactory);
    }

}
