package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCodeAndOrder;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCode;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class MonitorCallbackImpl {

    private final OrderMap<ObjectHashCode> monitorOrder;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public MonitorCallbackImpl(OrderMap<ObjectHashCode> monitorOrder,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.monitorOrder = monitorOrder;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void afterMonitorEnter(Object monitor, int inMethod, int position) {
        threadLocalWhenInTestAdapter.process(new RunAfter<>(new MonitorEnterEvent(
                inMethod, position),
                new SetObjectHashCodeAndOrder<>(monitorOrder, monitor)));
    }

    public void afterMonitorExit(Object monitor, int inMethod, int position) {
        threadLocalWhenInTestAdapter.process(new RunAfter<>(new MonitorExitEvent(
                inMethod, position),
                new SetObjectHashCodeAndOrder<>(monitorOrder, monitor)));
    }

}
