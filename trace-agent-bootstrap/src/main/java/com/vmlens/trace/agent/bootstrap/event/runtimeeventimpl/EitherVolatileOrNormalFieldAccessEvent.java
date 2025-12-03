package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public interface EitherVolatileOrNormalFieldAccessEvent extends RuntimeEvent , WithInMethodIdPositionReadWriteLockMap {

    void setOperation(int operation);
    void setMethodId(int methodId);
}
