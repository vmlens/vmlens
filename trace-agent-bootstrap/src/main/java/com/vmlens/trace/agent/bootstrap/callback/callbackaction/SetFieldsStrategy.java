package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

public interface SetFieldsStrategy<EVENT> {

    void setFields(EVENT event);
}
