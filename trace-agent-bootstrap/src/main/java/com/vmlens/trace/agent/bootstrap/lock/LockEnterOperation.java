package com.vmlens.trace.agent.bootstrap.lock;

/**
 * either LockEnter or TryLock
 */

public interface LockEnterOperation {

    LockEvent create(LockType lockType, Object object);

}
