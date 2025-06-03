package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;


import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

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
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.getReadWriteLock(name,desc);
    }
}
