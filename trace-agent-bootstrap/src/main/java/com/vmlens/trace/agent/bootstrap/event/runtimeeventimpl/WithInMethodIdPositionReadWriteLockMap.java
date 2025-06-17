package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

/**
 * All atomic events must implement this interface
 */

public interface WithInMethodIdPositionReadWriteLockMap {

    void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap);

}
