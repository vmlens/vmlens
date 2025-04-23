package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;


import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

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
