package com.anarsoft.trace.agent.runtime.classarraytransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlan;
import org.objectweb.asm.MethodVisitor;

public interface TransformMethodMethodCallFactory {

    MethodVisitor create(int methodId, MethodTransformPlan methodTransformPlan, MethodVisitor previous);

}
