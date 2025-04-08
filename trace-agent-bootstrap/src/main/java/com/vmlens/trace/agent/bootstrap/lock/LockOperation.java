package com.vmlens.trace.agent.bootstrap.lock;

public interface LockOperation {

    LockEvent create(LockType lockType, Object object);

}
