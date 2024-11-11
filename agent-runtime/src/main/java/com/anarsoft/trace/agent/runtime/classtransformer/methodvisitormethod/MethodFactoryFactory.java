package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethod;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactoryFactory;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;

public class MethodFactoryFactory implements MethodVisitorFactoryFactory {
    @Override
    public MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap) {
        return new MethodAnalyzeAndTransformFactory();
    }
}
