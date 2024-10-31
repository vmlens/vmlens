package com.anarsoft.trace.agent.runtime.classarraytransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classarraytransformer.MethodVisitorAdapter;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.ApplyAfterOperationMethodCallTarget;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlanBuilder;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.PlanElement;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.StackElement;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.stack.CallStack;
import com.vmlens.trace.agent.bootstrap.stack.StackImpl;
import org.objectweb.asm.MethodVisitor;

public class AnalyzeMethodMethodCall implements AnalyzeOrTransformMethodMethodCall {

    private final MethodTransformPlanBuilder methodTransformPlan;

    private final CallStack<StackElement> callStack = new StackImpl<>();

    public AnalyzeMethodMethodCall(MethodTransformPlanBuilder methodTransformPlan) {
        this.methodTransformPlan = methodTransformPlan;
    }

    public static MethodVisitor asMethodVisitor(MethodCallIdMap methodCallIdMap,
                                                MethodTransformPlanBuilder methodTransformPlan) {
        return new MethodVisitorAdapter(null, methodCallIdMap, new AnalyzeMethodMethodCall(methodTransformPlan));
    }

    @Override
    public void afterLocalLoad(int size) {
        PlanElement planElement = new PlanElement();
        methodTransformPlan.add(planElement);
        callStack.push(new StackElement(planElement));
    }

    @Override
    public void beforeMethodCall(int callArgumentSize, int returnSize,
                                 boolean isConstructorCall, int methodCallId) {

        // bei remove
        StackElement stackElement = callStack.backward(callArgumentSize);
        callStack.remove(callArgumentSize + 1);
        PlanElement planElement = new PlanElement();

        if (isConstructorCall) {
            stackElement.setForwardTo(planElement);
        } else {
            stackElement.addApplyAfterOperation(new ApplyAfterOperationMethodCallTarget(methodCallId));
        }


        methodTransformPlan.add(planElement);

        if (returnSize > 0) {
            callStack.push(new StackElement(planElement));
        }
    }

    @Override
    public void afterStackOperation(int opCode) {
        callStack.dup();
    }

    @Override
    public void afterNew() {
        StackElement stackElement = new StackElement();
        callStack.push(stackElement);
    }

    @Override
    public void afterMethodCall(int returnSize, int methodCallId) {
        // Nothing to do for analyze
    }
}
