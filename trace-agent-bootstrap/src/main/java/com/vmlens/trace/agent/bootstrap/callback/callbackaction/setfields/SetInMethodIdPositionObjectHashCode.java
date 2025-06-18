package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdPositionObjectHashCode;


public class SetInMethodIdPositionObjectHashCode<EVENT extends WithInMethodIdPositionObjectHashCode> implements SetFields<EVENT> {

    private final Object object;

    public SetInMethodIdPositionObjectHashCode(Object object) {
        this.object = object;
    }

    @Override
    public void setFields(EVENT event, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        long hashCode = System.identityHashCode(object);
        InMethodIdAndPosition toSet = threadLocalDataWhenInTest.inMethodIdAndPosition();
        if(toSet != null) {
            event.setInMethodIdPositionObjectHashCode(toSet.inMethodId(),toSet.position(),hashCode);
        } else {
            event.setInMethodIdPositionObjectHashCode(-1,-1,hashCode);
        }
    }
}
