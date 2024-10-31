package com.anarsoft.trace.agent.runtime.classarraytransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlan;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.objectweb.asm.MethodVisitor;

public class TransformMethodMethodCallFactoryImpl implements TransformMethodMethodCallFactory {

    private final MethodCallIdMap methodCallIdMap;

    public TransformMethodMethodCallFactoryImpl(MethodCallIdMap methodCallIdMap) {
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public MethodVisitor create(int methodId, MethodTransformPlan methodTransformPlan, MethodVisitor previous) {
        return TransformMethodMethodCall.asMethodVisitor(methodCallIdMap, methodTransformPlan, previous, methodId);

    }
}
