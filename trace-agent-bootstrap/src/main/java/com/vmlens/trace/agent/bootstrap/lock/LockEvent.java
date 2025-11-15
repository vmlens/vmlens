package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NoThreadOperationFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdPositionReadWriteLockMap;

public interface LockEvent extends NoThreadOperationFactory,
        WithInMethodIdPositionReadWriteLockMap {
}
