package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public class MethodCallbackFactoryFactoryAll implements MethodCallbackFactoryFactory {

    @Override
    public MethodCallbackFactory create(MethodVisitor methodVisitor) {
        return new MethodCallbackFactoryAll(methodVisitor);
    }
}
