package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithObjectHashCode;

public class SetObjectHashCode<EVENT extends WithObjectHashCode> implements SetFieldsStrategy<EVENT> {

    private final Object object;

    public SetObjectHashCode(Object object) {
        this.object = object;
    }

    @Override
    public void setFields(EVENT event) {
        long hashCode = System.identityHashCode(object);
        event.setObjectHashCode(hashCode);
    }
}
