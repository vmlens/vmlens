package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;

public interface MethodVisitorAnalyzeAndTransformFactoryFactory {

    MethodVisitorAnalyzeAndTransformFactory create(MethodCallIdMap methodCallIdMap);

}
