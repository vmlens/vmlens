package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockExitEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;



public class LockExitStrategy extends AbstractLockExitStrategy {

    private final LockType lockType;

    public LockExitStrategy(LockType lockType) {
        this.lockType = lockType;
    }


    @Override
    protected LockExitEvent createEvent(EnterExitContext context) {
        return new LockExitEvent(lockType,context.readWriteLockMap());
    }
}
