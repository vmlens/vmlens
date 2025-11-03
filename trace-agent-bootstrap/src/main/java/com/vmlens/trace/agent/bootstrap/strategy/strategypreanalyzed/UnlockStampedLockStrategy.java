package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockExitEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

import java.util.concurrent.locks.StampedLock;

import static com.vmlens.trace.agent.bootstrap.lock.LockTypes.STAMPED_READ_LOCK;
import static com.vmlens.trace.agent.bootstrap.lock.LockTypes.STAMPED_WRITE_LOCK;

public class UnlockStampedLockStrategy extends AbstractLockExitStrategy {

    @Override
    protected LockExitEvent createEvent(EnterExitContext context) {
        StampedLock lock = (StampedLock) context.object();
        if(lock.isReadLocked()) {
            return new LockExitEvent( STAMPED_READ_LOCK,  context.readWriteLockMap());
        } else {
            return new LockExitEvent( STAMPED_WRITE_LOCK,  context.readWriteLockMap());
        }
    }
}
