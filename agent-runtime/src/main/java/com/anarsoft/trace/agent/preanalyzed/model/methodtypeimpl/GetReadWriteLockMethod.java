package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.lock.LockTypes;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;

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
    public void add(String name, String desc, CallbackInNonBlockingMethod[] callbackInNonBlockingMethods, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.getReadWriteLock(name,desc);
    }
}
