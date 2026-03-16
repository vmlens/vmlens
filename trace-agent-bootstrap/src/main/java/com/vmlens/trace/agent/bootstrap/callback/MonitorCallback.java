package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.AfterMonitorEnterAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.AfterMonitorExitAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.BeforeMonitorExitAction;

public class MonitorCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();

    public static void afterMonitorEnter(Object monitor, int inMethod, int position) {
        AfterMonitorEnterAction afterMonitorEnterAction = new AfterMonitorEnterAction(monitor,inMethod,position);
        callbackActionProcessor.process(afterMonitorEnterAction);
    }

    public static void beforeMonitorExit(Object monitor, int inMethod, int position) {
        BeforeMonitorExitAction beforeMonitorExitAction = new BeforeMonitorExitAction(monitor,inMethod,position);
        callbackActionProcessor.process(beforeMonitorExitAction);
    }

    public static void afterMonitorExit() {
        callbackActionProcessor.process(new AfterMonitorExitAction());
    }

    // Visible for Test
    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        MonitorCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
