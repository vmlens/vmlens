package com.vmlens.trace.agent.bootstrap.callback;

public class ThreadStartCallback {

    private static volatile ThreadStartCallbackImpl threadStartCallbackImpl =
            new ThreadStartCallbackImpl(new CallbackState());

    public static void threadStart(Object newThread) {
        threadStartCallbackImpl.threadStart(newThread);
    }
}
