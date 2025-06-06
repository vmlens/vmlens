package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;

public class ArrayNonBlockingMethod  extends AbstractMethodType {

    public static final ArrayNonBlockingMethod ARRAY_NON_BLOCKING_READ = new ArrayNonBlockingMethod(MemoryAccessType.IS_READ);
    public static final ArrayNonBlockingMethod ARRAY_NON_BLOCKING_WRITE = new ArrayNonBlockingMethod(MemoryAccessType.IS_WRITE);
    public static final ArrayNonBlockingMethod ARRAY_NON_BLOCKING_READ_WRITE = new ArrayNonBlockingMethod(MemoryAccessType.IS_READ_WRITE);


    private final int operation;

    private ArrayNonBlockingMethod(int operation) {
        this.operation = operation;
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addNonBlockingArrayMethod(name,desc,operation);
    }
    
}
