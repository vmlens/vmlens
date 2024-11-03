package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classtransformer.plan.MethodTransformPlan;
import org.objectweb.asm.MethodVisitor;

public interface TransformMethodMethodCallFactory {

    MethodVisitor create(int methodId, MethodTransformPlan methodTransformPlan, MethodVisitor previous);

}
