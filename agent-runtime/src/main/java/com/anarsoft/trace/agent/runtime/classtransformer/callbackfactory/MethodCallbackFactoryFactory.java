package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public interface MethodCallbackFactoryFactory {

    MethodCallbackFactory create(MethodVisitor methodVisitor);

}
