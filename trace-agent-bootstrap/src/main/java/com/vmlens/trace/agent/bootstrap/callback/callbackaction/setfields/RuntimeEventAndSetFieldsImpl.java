package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public class RuntimeEventAndSetFieldsImpl<EVENT extends RuntimeEvent> implements RuntimeEventAndSetFields {

    private final EVENT runtimeEvent;
    private final SetFields<EVENT> setFieldsStrategy;

    public RuntimeEventAndSetFieldsImpl(EVENT runtimeEvent, SetFields<EVENT> setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    public EVENT applySetFields() {
        setFieldsStrategy.setFields(runtimeEvent);
        return runtimeEvent;
    }


}
