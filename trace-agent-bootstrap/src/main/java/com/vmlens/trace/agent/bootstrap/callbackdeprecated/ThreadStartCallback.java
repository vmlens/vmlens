package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallbackImpl;

public class ThreadStartCallback {

    private static volatile ThreadStartCallbackImpl threadStartCallbackImpl =
            new ThreadStartCallbackImpl(new ParallelizeBridgeForCallbackImpl());

    public static void threadStart(Object newThread) {
        threadStartCallbackImpl.threadStart(newThread);
    }

    public static void afterThreadStart() {
        threadStartCallbackImpl.afterThreadStart();
    }
}
