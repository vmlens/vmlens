package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.NonBlockingStrategy;

public class NonBlockingMethod extends AbstractMethodType  {

    public static final NonBlockingMethod NON_BLOCKING_READ = new NonBlockingMethod(MemoryAccessType.IS_READ);
    public static final NonBlockingMethod NON_BLOCKING_WRITE = new NonBlockingMethod(MemoryAccessType.IS_WRITE);
    public static final NonBlockingMethod NON_BLOCKING_READ_WRITE = new NonBlockingMethod(MemoryAccessType.IS_READ_WRITE);


    private final int operation;

    private NonBlockingMethod(int operation) {
        this.operation = operation;
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addPreAnalyzedMethod(name,desc,new NonBlockingStrategy(operation));
    }
}
