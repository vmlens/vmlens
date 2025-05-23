package com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public interface RuntimeEventAndSetFields {

    RuntimeEvent applySetFields(ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
