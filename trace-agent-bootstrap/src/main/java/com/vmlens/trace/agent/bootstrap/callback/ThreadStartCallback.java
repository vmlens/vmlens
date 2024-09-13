package com.vmlens.trace.agent.bootstrap.callback;

public class ThreadStartCallback {

    private static volatile ThreadStartCallbackImpl threadStartCallbackImpl = new ThreadStartCallbackImpl();

    public static void threadStart(Object newThread) {
        threadStartCallbackImpl.threadStart(newThread);
    }
}
