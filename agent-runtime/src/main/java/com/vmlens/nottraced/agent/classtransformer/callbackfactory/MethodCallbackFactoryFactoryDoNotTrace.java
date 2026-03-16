package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public class MethodCallbackFactoryFactoryDoNotTrace implements MethodCallbackFactoryFactory {

    private final DoNotTraceType doNotTraceType;

    public MethodCallbackFactoryFactoryDoNotTrace(DoNotTraceType doNotTraceType) {
        this.doNotTraceType = doNotTraceType;
    }

    @Override
    public MethodCallbackFactory create(MethodVisitor methodVisitor) {
        return new MethodCallbackFactoryDoNotTrace(methodVisitor,doNotTraceType);
    }

}
