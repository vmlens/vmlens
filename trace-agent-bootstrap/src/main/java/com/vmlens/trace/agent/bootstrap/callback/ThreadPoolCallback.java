package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.ThreadPoolCallbackImpl;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;

public class ThreadPoolCallback {

    private static volatile ThreadPoolCallbackImpl threadPoolCallbackImpl = new ThreadPoolCallbackImpl();

    public static boolean start(Object pool, Object task,int methodId) {
        if(canProcess()) {
            startProcess();
            try {
               return threadPoolCallbackImpl.start(pool, task, methodId);
            } finally {
                stopProcess();
            }
        }
        return false;
    }

    public static void join(Object task, int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                threadPoolCallbackImpl.join(task, methodId);
            } finally {
                stopProcess();
            }
        }
    }

    // visible for test
    public static void setThreadPoolCallbackImpl(ThreadPoolCallbackImpl threadPoolCallbackImpl) {
        ThreadPoolCallback.threadPoolCallbackImpl = threadPoolCallbackImpl;
    }
}
