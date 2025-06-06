package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public interface MethodCallbackFactoryFactory {

    MethodCallbackFactory create(MethodVisitor methodVisitor);

}
