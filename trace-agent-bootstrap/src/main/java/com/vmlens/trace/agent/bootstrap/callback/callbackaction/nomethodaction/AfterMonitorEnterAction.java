package com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.strategy.MonitorContext;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.monitorEnter;

public class AfterMonitorEnterAction extends NoMethodAction {

    private final Object monitor;
    private final int inMethod;
    private final int position;

    public AfterMonitorEnterAction(Object monitor, int inMethod, int position) {
        this.monitor = monitor;
        this.inMethod = inMethod;
        this.position = position;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        MonitorContext methodContext = new MonitorContext(monitor,inMethod,inTestActionProcessor);
        monitorEnter(methodContext,position);
    }
}
