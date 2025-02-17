package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfieldsstrategy;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;

public class RuntimeEventAndSetFieldsStrategyImpl<EVENT extends RuntimeEvent> implements RuntimeEventAndSetFieldsStrategy {

    private final EVENT runtimeEvent;
    private final SetFieldsStrategy<EVENT> setFieldsStrategy;

    public RuntimeEventAndSetFieldsStrategyImpl(EVENT runtimeEvent, SetFieldsStrategy<EVENT> setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    public RuntimeEvent applySetFields() {
        setFieldsStrategy.setFields(runtimeEvent);
        return runtimeEvent;
    }


}
