package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;

public class VmlensApiCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();

    public static void close(Object obj) {
        callbackActionProcessor.vmlensApiClose(obj);
    }

    public static boolean hasNext(Object obj) {
        return callbackActionProcessor.vmlensApiHasNext(obj);
    }

    public static void automaticTestSuccess(Object obj, int id, String className) {
        callbackActionProcessor.automaticTestSuccess(id, className);
    }

    public static void automaticTestMethod(Object obj, int id,  int automaticTestMethodId, int automaticTestType) {
        callbackActionProcessor.automaticTestMethod(id,automaticTestMethodId, automaticTestType);
    }

    // Visible for Test

    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        VmlensApiCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
