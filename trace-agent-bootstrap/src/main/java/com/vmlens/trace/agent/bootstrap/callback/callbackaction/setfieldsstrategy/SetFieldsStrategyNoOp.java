package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfieldsstrategy;

public class SetFieldsStrategyNoOp<EVENT> implements SetFieldsStrategy<EVENT> {

    @Override
    public void setFields(EVENT event) {
        // Nothing to do
    }
}
