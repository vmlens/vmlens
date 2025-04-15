package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.MonitorCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;

public class MonitorCallback {

    private static volatile MonitorCallbackImpl monitorCallbackImpl = new MonitorCallbackImpl(
            new ThreadLocalWhenInTestAdapterImpl());

    public static void afterMonitorEnter(Object monitor, int inMethod, int position) {
        if(canProcess()) {
            startProcess();
            try {
                monitorCallbackImpl.afterMonitorEnter(monitor, inMethod, position);
            } finally {
                stopProcess();
            }
        }
    }

    public static void afterMonitorExit(Object monitor, int inMethod, int position) {
        if(canProcess()) {
            startProcess();
            try {
                monitorCallbackImpl.afterMonitorExit(monitor, inMethod, position);
            } finally {
                stopProcess();
            }
        }
    }

    // Visible for Test
    public static void setMonitorCallbackImpl(MonitorCallbackImpl monitorCallbackImpl) {
        MonitorCallback.monitorCallbackImpl = monitorCallbackImpl;
    }
}
