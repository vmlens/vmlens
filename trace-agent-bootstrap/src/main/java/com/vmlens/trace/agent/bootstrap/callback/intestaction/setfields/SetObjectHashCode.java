package com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithObjectHashCode;

public class SetObjectHashCode<EVENT extends WithObjectHashCode> implements SetFields<EVENT> {

    private final Object object;

    public SetObjectHashCode(Object object) {
        this.object = object;
    }

    @Override
    public void setFields(EVENT event, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        long hashCode = System.identityHashCode(object);
        event.setObjectHashCode(hashCode);
    }
}
