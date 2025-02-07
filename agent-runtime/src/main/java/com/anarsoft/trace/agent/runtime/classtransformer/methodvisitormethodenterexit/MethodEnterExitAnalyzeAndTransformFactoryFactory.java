package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactoryFactory;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;

public class MethodEnterExitAnalyzeAndTransformFactoryFactory implements MethodVisitorAnalyzeAndTransformFactoryFactory {
    @Override
    public MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap) {
        return new MethodAnalyzeAndTransformFactory();
    }
}
