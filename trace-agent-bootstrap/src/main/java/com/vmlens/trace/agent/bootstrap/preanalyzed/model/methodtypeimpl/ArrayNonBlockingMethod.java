package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;

public class ArrayNonBlockingMethod  extends AbstractMethodType {

    public static final ArrayNonBlockingMethod ARRAY_NON_BLOCKING_READ = new ArrayNonBlockingMethod(MemoryAccessType.IS_READ);
    public static final ArrayNonBlockingMethod ARRAY_NON_BLOCKING_WRITE = new ArrayNonBlockingMethod(MemoryAccessType.IS_WRITE);
    public static final ArrayNonBlockingMethod ARRAY_NON_BLOCKING_READ_WRITE = new ArrayNonBlockingMethod(MemoryAccessType.IS_READ_WRITE);


    private final int operation;

    private ArrayNonBlockingMethod(int operation) {
        this.operation = operation;
    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addNonBlockingArrayMethod(context.name(),context.desc(),operation);
    }
    
}
