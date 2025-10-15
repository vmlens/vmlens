package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.ThreadPoolJoinAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction.ThreadPoolStartAction;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolCallback {

    private static volatile CallbackActionProcessor callbackActionProcessor = new CallbackActionProcessorImpl();
    private static final AtomicInteger threadCount = new AtomicInteger();

    public static boolean start(Object pool, Object task,int methodId) {
        return callbackActionProcessor.process(new ThreadPoolStartAction(pool, (Runnable) task, threadCount));
    }

    public static void join(Object task, int methodId) {
        callbackActionProcessor.process(new ThreadPoolJoinAction(task));
    }

    public static void joinExit() {

    }

    // visible for test


    public static void setCallbackActionProcessor(CallbackActionProcessor callbackActionProcessor) {
        ThreadPoolCallback.callbackActionProcessor = callbackActionProcessor;
    }
}
