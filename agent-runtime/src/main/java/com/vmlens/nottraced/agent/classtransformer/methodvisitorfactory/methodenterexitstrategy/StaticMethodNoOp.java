package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;

/**
 *
 * This is for the case bytecode lower 1.5 and during retransform, e.g. no methods can be added
 */
public class StaticMethodNoOp implements CreateCalleeFactoryProvider {


    @Override
    public CalleeFactory createCalleeFactory(MethodVisitor methodVisitor, String className) {
        return null;
    }
}
