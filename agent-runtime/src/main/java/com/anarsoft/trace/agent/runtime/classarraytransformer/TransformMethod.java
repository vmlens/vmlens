package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlan;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.objectweb.asm.MethodVisitor;

public class TransformMethod implements AnalyzeOrTransformMethod {

    private final MethodTransformPlan methodTransformPlan;
    private final CallbackCallFactory callbackCallFactory;

    public TransformMethod(MethodTransformPlan methodTransformPlan, CallbackCallFactory callbackCallFactory) {
        this.methodTransformPlan = methodTransformPlan;
        this.callbackCallFactory = callbackCallFactory;
    }

    public static MethodVisitor asMethodVisitor(MethodCallIdMap methodCallIdMap,
                                                MethodTransformPlan methodTransformPlan,
                                                MethodVisitor methodVisitor) {
        return new MethodVisitorAdapter(methodVisitor, methodCallIdMap, new TransformMethod(methodTransformPlan,
                new CallbackCallFactory(methodVisitor)));
    }

    @Override
    public void afterLocalLoad(int size) {
        methodTransformPlan.apply(callbackCallFactory);
    }

    @Override
    public void beforeMethodCall(int callArgumentSize, int returnSize,
                                 boolean isConstructorCall, int methodCallId) {
        callbackCallFactory.beforeMethodCall(methodCallId);
    }

    @Override
    public void afterMethodCall(int returnSize, int methodCallId) {
        callbackCallFactory.afterMethodCall(methodCallId);
        methodTransformPlan.apply(callbackCallFactory);
    }


    @Override
    public void afterStackOperation(int opCode) {
        // Nothing to do for transform
    }

    @Override
    public void afterNew() {
        // Nothing to do for transform
    }

}
