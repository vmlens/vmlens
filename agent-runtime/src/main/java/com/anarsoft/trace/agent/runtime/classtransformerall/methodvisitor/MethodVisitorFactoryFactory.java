package com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;

public interface MethodVisitorFactoryFactory {

    MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap);

}
