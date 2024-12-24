package com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethod;

import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorFactoryFactory;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;

public class MethodFactoryFactory implements MethodVisitorFactoryFactory {
    @Override
    public MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap) {
        return new MethodAnalyzeAndTransformFactory();
    }
}
