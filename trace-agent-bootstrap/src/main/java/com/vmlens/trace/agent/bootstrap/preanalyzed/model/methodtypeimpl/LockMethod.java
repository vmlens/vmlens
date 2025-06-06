package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.lock.*;

public class LockMethod  extends AbstractMethodType  {

    public static final LockMethod ENTER_REENTRANT_LOCK = new LockMethod(new LockEnter(), LockTypes.REENTRANT_LOCK);
    public static final LockMethod EXIT_REENTRANT_LOCK = new LockMethod(new LockExit(), LockTypes.REENTRANT_LOCK);

    public static final LockMethod ENTER_READ_LOCK = new LockMethod(new LockEnter(), LockTypes.READ_LOCK);
    public static final LockMethod EXIT_READ_LOCK = new LockMethod(new LockExit(), LockTypes.READ_LOCK);

    public static final LockMethod ENTER_WRITE_LOCK = new LockMethod(new LockEnter(), LockTypes.WRITE_LOCK);
    public static final LockMethod EXIT_WRITE_LOCK = new LockMethod(new LockExit(), LockTypes.WRITE_LOCK);

    private final LockOperation lockOperation;
    private final LockType lockType;

    private LockMethod(LockOperation lockOperation, LockType lockType) {
        this.lockOperation = lockOperation;
        this.lockType = lockType;
    }

    @Override
    public void add(String name, String desc,FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addLockMethod(name,desc,lockType,lockOperation);
    }
}
