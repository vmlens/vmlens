package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public class MethodCallbackFactoryFactoryPreAnalyzed implements MethodCallbackFactoryFactory {

    @Override
    public MethodCallbackFactory create(MethodVisitor methodVisitor) {
        return new MethodCallbackFactoryPreAnalyzed(methodVisitor);
    }

}
