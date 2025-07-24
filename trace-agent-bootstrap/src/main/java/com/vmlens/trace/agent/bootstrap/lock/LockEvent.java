package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdPositionReadWriteLockMap;

public interface LockEvent extends InterleaveActionFactory,
        WithInMethodIdPositionReadWriteLockMap {
}
