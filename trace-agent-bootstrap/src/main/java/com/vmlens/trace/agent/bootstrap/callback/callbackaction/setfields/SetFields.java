package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public interface SetFields<EVENT> {

    void setFields(EVENT event, ThreadLocalWhenInTest threadLocalDataWhenInTest);
}
