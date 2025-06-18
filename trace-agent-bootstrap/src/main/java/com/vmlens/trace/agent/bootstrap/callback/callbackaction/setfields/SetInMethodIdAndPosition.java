package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdPositionReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class SetInMethodIdAndPosition<EVENT extends WithInMethodIdPositionReadWriteLockMap> implements SetFields<EVENT> {

    private final ReadWriteLockMap readWriteLockMap;

    public SetInMethodIdAndPosition(ReadWriteLockMap readWriteLockMap) {
        this.readWriteLockMap = readWriteLockMap;
    }

    @Override
    public void setFields(EVENT event, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        InMethodIdAndPosition toSet = threadLocalDataWhenInTest.inMethodIdAndPosition();
        event.setInMethodIdAndPosition(toSet.inMethodId(),toSet.position(),readWriteLockMap);

    }
}
