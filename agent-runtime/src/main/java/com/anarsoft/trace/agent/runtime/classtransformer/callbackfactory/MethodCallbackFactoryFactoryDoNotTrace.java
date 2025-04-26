package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public class MethodCallbackFactoryFactoryDoNotTrace implements MethodCallbackFactoryFactory {

    @Override
    public MethodCallbackFactory create(MethodVisitor methodVisitor) {
        return new MethodCallbackFactoryDoNotTrace(methodVisitor);
    }

}
