package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;

public class TransformerStrategyAll implements TransformerStrategy {

    private final MethodCallIdMap methodCallIdMap;
    private final FieldOwnerAndNameToIntMap fieldIdMap;

    public TransformerStrategyAll(MethodCallIdMap methodCallIdMap, FieldOwnerAndNameToIntMap fieldIdMap) {
        this.methodCallIdMap = methodCallIdMap;
        this.fieldIdMap = fieldIdMap;
    }

    @Override
    public byte[] transform(TransformerContext context) {
        ClassTransformer classTransformer = ClassTransformer.createAll(methodCallIdMap, fieldIdMap);
        return classTransformer.transform(context.classfileBuffer(), context.name());
    }
}
