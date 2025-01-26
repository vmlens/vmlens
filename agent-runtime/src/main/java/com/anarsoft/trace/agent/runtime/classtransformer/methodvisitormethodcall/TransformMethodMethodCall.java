package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.plan.MethodTransformPlan;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import org.objectweb.asm.MethodVisitor;

public class TransformMethodMethodCall implements AnalyzeOrTransformMethodMethodCall {

    private final MethodTransformPlan methodTransformPlan;
    private final MethodCallbackFactory callbackCallFactory;
    private final int inMethodId;
    private int position;

    public TransformMethodMethodCall(MethodTransformPlan methodTransformPlan, MethodCallbackFactory callbackCallFactory, int inMethodId) {
        this.methodTransformPlan = methodTransformPlan;
        this.callbackCallFactory = callbackCallFactory;
        this.inMethodId = inMethodId;
    }

    public static MethodVisitor asMethodVisitor(MethodCallIdMap methodCallIdMap,
                                                MethodTransformPlan methodTransformPlan,
                                                MethodVisitor methodVisitor,
                                                int inMethodId) {
        return new MethodVisitorAdapter(methodVisitor, methodCallIdMap,
                new TransformMethodMethodCall(methodTransformPlan,
                new MethodCallbackFactory(methodVisitor), inMethodId));
    }

    @Override
    public void afterLocalLoad(int size) {
        methodTransformPlan.apply(callbackCallFactory);
    }

    @Override
    public void beforeMethodCall(int callArgumentSize, int returnSize,
                                 CallType callType, int methodCallId) {
        callbackCallFactory.beforeMethodCall(methodCallId);
    }

    @Override
    public void afterMethodCall(int returnSize, int methodCallId) {
        callbackCallFactory.afterMethodCall(inMethodId, position, methodCallId);
        methodTransformPlan.apply(callbackCallFactory);
        position++;
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
