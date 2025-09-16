package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.AfterMonitorEnterAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl.AfterMonitorExitAction;

public class MonitorCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();

    public static void afterMonitorEnter(Object monitor, int inMethod, int position) {
        AfterMonitorEnterAction afterMonitorEnterAction = new AfterMonitorEnterAction(monitor,inMethod,position);
        callbackActionProcessor.process(afterMonitorEnterAction);
    }

    public static void afterMonitorExit(Object monitor, int inMethod, int position) {
        AfterMonitorExitAction afterMonitorExitAction = new AfterMonitorExitAction(monitor,inMethod,position);
        callbackActionProcessor.process(afterMonitorExitAction);
    }

    // Visible for Test
    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        MonitorCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
