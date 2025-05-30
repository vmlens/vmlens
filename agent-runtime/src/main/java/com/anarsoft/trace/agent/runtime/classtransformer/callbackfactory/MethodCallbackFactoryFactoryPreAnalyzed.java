package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public class MethodCallbackFactoryFactoryPreAnalyzed implements MethodCallbackFactoryFactory {

    private final MethodEnterStrategy methodEnterStrategy;

    public MethodCallbackFactoryFactoryPreAnalyzed(MethodEnterStrategy methodEnterStrategy) {
        this.methodEnterStrategy = methodEnterStrategy;
    }

    @Override
    public MethodCallbackFactory create(MethodVisitor methodVisitor) {
        return new MethodCallbackFactoryPreAnalyzed(methodEnterStrategy,methodVisitor);
    }

}
