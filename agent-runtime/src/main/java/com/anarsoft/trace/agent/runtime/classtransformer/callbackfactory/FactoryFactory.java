package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public interface FactoryFactory {

    MethodCallbackFactory create(MethodVisitor methodVisitor);

}
