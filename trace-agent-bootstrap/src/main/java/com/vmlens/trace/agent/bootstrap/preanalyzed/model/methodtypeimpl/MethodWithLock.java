package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.lock.LockTypes;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.MethodWithLockStrategy;

/**
 *
 * for concurrent hash map and hash set
 *
 */

public class MethodWithLock extends AbstractMethodType {

    public static final MethodWithLock METHOD_WITH_READ_LOCK = new MethodWithLock(LockTypes.READ_LOCK);
    public static final MethodWithLock METHOD_WITH_WRITE_LOCK = new MethodWithLock(LockTypes.WRITE_LOCK);

    private final ReadOrWriteLock lockType;

    private MethodWithLock(ReadOrWriteLock lockType) {
        this.lockType = lockType;
    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addPreAnalyzedMethod(context.name(),context.desc(),new MethodWithLockStrategy(lockType));
    }
}
