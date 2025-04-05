package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.lock.LockTypes;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;

/**
 *
 * for concurrent hash map and hash set
 *
 */

public class MethodWithLock extends AbstractMethodType {

    public static final MethodWithLock READ_LOCK = new MethodWithLock(LockTypes.READ_LOCK);
    public static final MethodWithLock WRITE_LOCK = new MethodWithLock(LockTypes.WRITE_LOCK);

    private final ReadOrWriteLock lockType;

    private MethodWithLock(ReadOrWriteLock lockType) {
        this.lockType = lockType;
    }

    @Override
    public void add(String name, String desc, CallbackInNonBlockingMethod[] callbackInNonBlockingMethods,
                    FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addMethodWithLock(name,desc,lockType);
    }
}
