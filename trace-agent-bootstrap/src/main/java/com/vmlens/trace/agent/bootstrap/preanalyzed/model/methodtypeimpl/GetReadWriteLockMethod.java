package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;


import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.GetReadWriteLockMethodStrategy;

/**
 *
 * for ReadWriteLock
 *      readLock()
 *      writeLock()
 */

public class GetReadWriteLockMethod  extends AbstractMethodType  {

    public static final GetReadWriteLockMethod GET_READ_WRITE_LOCK = new GetReadWriteLockMethod();


    private GetReadWriteLockMethod() {
    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addPreAnalyzedMethod(context.name(),context.desc(), new GetReadWriteLockMethodStrategy());
    }
}
