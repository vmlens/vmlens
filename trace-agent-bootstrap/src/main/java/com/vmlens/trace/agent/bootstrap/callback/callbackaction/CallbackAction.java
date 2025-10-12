package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public interface CallbackAction {

    void execute(InTestActionProcessor inTestActionProcessor);
    Integer isFirstMethodInThread(CheckIsThreadRun checkIsThreadRun);
    boolean couldBeLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest);


    RuntimeEvent isLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest, int stackTraceDepth);
}
