package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.anarsoft.trace.agent.runtime.classtransformer.MethodVisitorAnalyzeAndTransformFactoryMap;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;
import org.objectweb.asm.MethodVisitor;

public interface SelectMethodVisitorFactoryStrategy {

    MethodVisitor create(FactoryContext context,
                         MethodVisitor previous,
                         MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                         MethodCallIdMap methodCallIdMap);

}
