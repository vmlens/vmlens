package com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public abstract class NoMethodAction implements CallbackAction {

    @Override
    public Integer isFirstMethodInThread(CheckIsThreadRun checkIsThreadRun) {
        return null;
    }

    @Override
    public boolean couldBeLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest) {
        return false;
    }

    @Override
    public RuntimeEvent isLastMethodInThread(ThreadLocalWhenInTest dataWhenInTest, int stackTraceDepth) {
        return null;
    }
}
