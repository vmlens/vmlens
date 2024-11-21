package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;

public interface MethodVisitorFactoryFactory {

    MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap);

}
