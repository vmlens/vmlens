package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.MonitorContext;

import static com.vmlens.trace.agent.bootstrap.strategy.strategyall.EventUtil.monitorEnter;
import static com.vmlens.trace.agent.bootstrap.strategy.strategyall.EventUtil.monitorExit;


public class MonitorCallbackImpl {

    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public MonitorCallbackImpl(ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void afterMonitorEnter(Object monitor, int inMethod, int position) {
        MonitorContext monitorContext = new MonitorContext(monitor,inMethod,threadLocalWhenInTestAdapter);
        monitorEnter(monitorContext,position);
    }

    public void afterMonitorExit(Object monitor, int inMethod, int position) {
        MonitorContext monitorContext = new MonitorContext(monitor,inMethod,threadLocalWhenInTestAdapter);
        monitorExit(monitorContext,position);
    }

}
