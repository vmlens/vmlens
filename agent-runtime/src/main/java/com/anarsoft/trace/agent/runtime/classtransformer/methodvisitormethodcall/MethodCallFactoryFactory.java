package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactoryFactory;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;

public class MethodCallFactoryFactory implements MethodVisitorFactoryFactory {
    @Override
    public MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap) {
        return new MethodCallAnalyzeAndTransformFactory(methodCallIdMap);
    }
}
