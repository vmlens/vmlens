package com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.strategy.MonitorContext;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.monitorExit;

public class AfterMonitorExitAction implements CallbackAction  {

    private final Object monitor;
    private final int inMethod;
    private final int position;

    public AfterMonitorExitAction(Object monitor, int inMethod, int position) {
        this.monitor = monitor;
        this.inMethod = inMethod;
        this.position = position;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        MonitorContext methodContext = new MonitorContext(monitor,inMethod,inTestActionProcessor);
        monitorExit(methodContext,position);
    }

}
