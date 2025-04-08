package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockEnterEvent;

public class LockEnter implements LockOperation {

    @Override
    public LockEvent create(LockType lockType, Object object) {
        return new LockEnterEvent(lockType,object);
    }
}
