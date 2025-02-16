package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;

public interface ThreadLocalWhenInTestAdapter {
    void process(CallbackAction callbackAction);

    // plus method newTask


}
