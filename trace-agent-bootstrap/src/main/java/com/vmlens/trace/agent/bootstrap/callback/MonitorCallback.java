package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.MonitorCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class MonitorCallback {

    private static volatile MonitorCallbackImpl monitorCallbackImpl = new MonitorCallbackImpl(new OrderMap<>(),
            new ThreadLocalWhenInTestAdapterImpl());

    public static void afterMonitorEnter(Object monitor, int inMethod, int position) {
        monitorCallbackImpl.afterMonitorEnter(monitor, inMethod, position);
    }

    public static void afterMonitorExit(Object monitor, int inMethod, int position) {
        monitorCallbackImpl.afterMonitorExit(monitor, inMethod, position);
    }

    // Visible for Test
    public static void setMonitorCallbackImpl(MonitorCallbackImpl monitorCallbackImpl) {
        MonitorCallback.monitorCallbackImpl = monitorCallbackImpl;
    }
}
