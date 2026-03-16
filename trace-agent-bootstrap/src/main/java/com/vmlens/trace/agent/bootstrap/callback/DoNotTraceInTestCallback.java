package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;

public class DoNotTraceInTestCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();

    public static void methodEnter(Object object, int methodId) {
        callbackActionProcessor.startDoNotTraceInTest();
    }

    public static void methodExit(Object object, int methodId) {
        callbackActionProcessor.endDoNotTraceInTest();
    }

    public static void onFinally(Object object, int methodId) {
        callbackActionProcessor.endDoNotTraceInTest();
    }



}
