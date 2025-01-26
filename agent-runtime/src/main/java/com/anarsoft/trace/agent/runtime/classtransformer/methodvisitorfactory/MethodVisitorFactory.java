package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.anarsoft.trace.agent.runtime.classtransformer.MethodVisitorAnalyzeAndTransformFactoryMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorFactory {

    MethodVisitor create(MethodVisitorFactoryContext context,
                         MethodVisitor previous,
                         MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                         MethodCallIdMap methodCallIdMap);

}
