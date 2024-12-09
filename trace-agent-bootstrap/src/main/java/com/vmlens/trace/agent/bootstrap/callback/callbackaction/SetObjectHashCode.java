package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithObjectHashCode;

public class SetObjectHashCode<EVENT extends WithObjectHashCode> implements SetFieldsStrategy<EVENT> {
    @Override
    public void setFields(EVENT event) {
        long hashCode = System.identityHashCode(event);
        event.setObjectHashCode(hashCode);
    }
}
