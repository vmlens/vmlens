package com.anarsoft.trace.agent.preanalyzed.builder;


import com.vmlens.trace.agent.bootstrap.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;

public interface FactoryCollectionPreAnalyzedFactoryBuilder {

    void getReadWriteLock(String name, String desc);

    void addMethodWithLock(String name, String desc, ReadOrWriteLock lockType);

    void addNonBlockingMethod(String name, String desc, int operation);

    void addLockMethod(String name, String desc, LockType lockType, LockOperation lockOperation);

    void addThreadStart(String name, String desc);

    void addThreadJoin(String name, String desc);

    void notYetImplemented(String name, String desc);

    void noOpWhenMethodNotFound();


}
