package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.CallbackInNonBlockingMethod;
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

    public static final GetReadWriteLockMethod GET_READ_LOCK = new GetReadWriteLockMethod(LockTypes.READ_LOCK);
    public static final GetReadWriteLockMethod GET_WRITE_LOCK = new GetReadWriteLockMethod(LockTypes.WRITE_LOCK);

    private final ReadOrWriteLock lockType;

    private GetReadWriteLockMethod(ReadOrWriteLock lockType) {
        this.lockType = lockType;
    }

    @Override
    public void add(String name, String desc, CallbackInNonBlockingMethod[] callbackInNonBlockingMethods, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.getReadWriteLock(name,desc,lockType);
    }
}
