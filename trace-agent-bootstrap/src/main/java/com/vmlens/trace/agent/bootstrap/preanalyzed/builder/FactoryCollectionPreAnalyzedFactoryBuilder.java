package com.vmlens.trace.agent.bootstrap.preanalyzed.builder;


import com.vmlens.trace.agent.bootstrap.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;
import com.vmlens.trace.agent.bootstrap.strategy.threadpool.StrategyThreadPool;

public interface FactoryCollectionPreAnalyzedFactoryBuilder {

    void getReadWriteLock(String name, String desc);

    void addMethodWithLock(String name, String desc, ReadOrWriteLock lockType);

    void addNonBlockingMethod(String name, String desc, int operation);

    void addNonBlockingArrayMethod(String name, String desc, int operation);

    void addLockMethod(String name, String desc, LockType lockType, LockOperation lockOperation);

    void addThreadStart(String name, String desc);

    void addThreadJoin(String name, String desc);

    void notYetImplemented(String name, String desc);

    void noOpWhenMethodNotFound();

    void setThreadPoolStart(String name, String desc);

    void addThreadPoolJoin(String name, String desc, StrategyThreadPool strategyThreadPool);



}
