package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

public class SetFieldsNoOp<EVENT> implements SetFields<EVENT> {

    @Override
    public void setFields(EVENT event) {
        // Nothing to do
    }
}
