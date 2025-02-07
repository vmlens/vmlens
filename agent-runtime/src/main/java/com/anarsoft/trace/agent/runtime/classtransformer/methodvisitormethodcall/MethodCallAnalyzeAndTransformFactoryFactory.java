package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactoryFactory;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;

public class MethodCallAnalyzeAndTransformFactoryFactory implements MethodVisitorAnalyzeAndTransformFactoryFactory {
    @Override
    public MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap) {
        return new MethodCallAnalyzeAndTransformFactory(methodCallIdMap);
    }
}
