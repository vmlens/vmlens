package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public class MethodCallbackFactoryFactoryDoNotTrace implements MethodCallbackFactoryFactory {

    @Override
    public MethodCallbackFactory create(MethodVisitor methodVisitor) {
        return new MethodCallbackFactoryDoNotTrace(methodVisitor);
    }

}
