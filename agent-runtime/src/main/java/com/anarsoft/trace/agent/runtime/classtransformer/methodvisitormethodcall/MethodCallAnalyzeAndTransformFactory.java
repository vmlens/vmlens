package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactoryContext;
import com.anarsoft.trace.agent.runtime.classtransformer.plan.MethodTransformPlanBuilder;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import org.objectweb.asm.MethodVisitor;

public class MethodCallAnalyzeAndTransformFactory implements MethodVisitorAnalyzeAndTransformFactory {

    private final MethodCallIdMap methodCallIdMap;
    private final MethodTransformPlanBuilder methodTransformPlanBuilder = new MethodTransformPlanBuilder();

    public MethodCallAnalyzeAndTransformFactory(MethodCallIdMap methodCallIdMap) {
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public MethodVisitor createTransform(MethodVisitorFactoryContext transformFactoryContext, MethodVisitor current) {
        return TransformMethodMethodCall.asMethodVisitor(methodCallIdMap, methodTransformPlanBuilder.build(),
                current, transformFactoryContext.methodId());
    }

    @Override
    public MethodVisitor createAnalyze(int methodId, MethodVisitor previous) {
        return AnalyzeMethodMethodCall.asMethodVisitor(previous,
                methodCallIdMap,
                methodTransformPlanBuilder);
    }

    // Visible for Test
    public MethodTransformPlanBuilder methodTransformPlanBuilder() {
        return methodTransformPlanBuilder;
    }
}
