package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit;

import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.FactoryContext;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.TransformFactory;
import org.objectweb.asm.MethodVisitor;

public class MethodEnterExitTransformFactory implements TransformFactory {

    private final int tryCatchBlockCount;
    private final MethodCallbackFactoryFactory factoryFactory;

    public MethodEnterExitTransformFactory(int tryCatchBlockCount,
                                           MethodCallbackFactoryFactory factoryFactory) {
        this.tryCatchBlockCount = tryCatchBlockCount;
        this.factoryFactory = factoryFactory;
    }

    @Override
    public MethodVisitor create(FactoryContext factoryContext, MethodVisitor previous) {
        return new MethodEnterExitTransform(factoryContext.methodId(), tryCatchBlockCount,
                factoryContext.useExpandedFrames(), previous, factoryContext.isStatic(),
                factoryContext.isConstructor(), factoryContext.className(),
                factoryContext.description(), factoryFactory);
    }

}
