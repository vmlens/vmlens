package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class SetFieldsNoOp<EVENT> implements SetFields<EVENT> {

    @Override
    public void setFields(EVENT event, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        // Nothing to do
    }
}
