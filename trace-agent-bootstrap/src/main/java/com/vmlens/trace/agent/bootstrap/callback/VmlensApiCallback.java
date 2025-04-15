package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.VmlensApiCallbackImpl;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;

public class VmlensApiCallback {

    private static volatile VmlensApiCallbackImpl vmlensApiCallback = new VmlensApiCallbackImpl();

    public static void close(Object obj) {
            startProcess();
            try {
                vmlensApiCallback.close(obj);
            } finally {
                stopProcess();
            }
    }

    public static boolean hasNext(Object obj) {
            startProcess();
            try {
                return vmlensApiCallback.hasNext(obj);
            } finally {
                stopProcess();
            }
    }

    // Visible for Test
    public static void setVmlensApiCallback(VmlensApiCallbackImpl vmlensApiCallback) {
        VmlensApiCallback.vmlensApiCallback = vmlensApiCallback;
    }
}
