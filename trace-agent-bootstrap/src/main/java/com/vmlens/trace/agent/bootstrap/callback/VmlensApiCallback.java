package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.VmlensApiCallbackImpl;

public class VmlensApiCallback {

    private static volatile VmlensApiCallbackImpl vmlensApiCallback = new VmlensApiCallbackImpl();

    public static void close(Object obj) {
        vmlensApiCallback.close(obj);
    }

    public static boolean hasNext(Object obj) {
        return vmlensApiCallback.hasNext(obj);
    }

    // Visible for Test
    public static void setVmlensApiCallback(VmlensApiCallbackImpl vmlensApiCallback) {
        VmlensApiCallback.vmlensApiCallback = vmlensApiCallback;
    }
}
