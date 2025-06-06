package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockExitEvent;

public class LockExit implements LockOperation {

    @Override
    public LockEvent create(LockType lockType, Object object) {
        return new LockExitEvent(lockType,object);
    }

}
