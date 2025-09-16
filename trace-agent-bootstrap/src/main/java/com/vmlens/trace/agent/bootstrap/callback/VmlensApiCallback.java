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

    // Visible for Test

    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        VmlensApiCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
