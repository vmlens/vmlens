package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;


public class MonitorCallbackImpl {

    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public MonitorCallbackImpl(ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void afterMonitorEnter(Object monitor, int inMethod, int position) {
        threadLocalWhenInTestAdapter.process(new RunAfter<>(new MonitorEnterEvent(
                inMethod, position),
                new SetObjectHashCode<>(monitor)));
    }

    public void afterMonitorExit(Object monitor, int inMethod, int position) {
        threadLocalWhenInTestAdapter.process(new RunAfter<>(new MonitorExitEvent(
                inMethod, position),
                new SetObjectHashCode(monitor)));
    }

}
