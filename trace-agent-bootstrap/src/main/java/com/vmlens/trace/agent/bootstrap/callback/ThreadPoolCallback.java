package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.ThreadPoolCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;

public class ThreadPoolCallback {

    private static volatile ThreadPoolCallbackImpl threadPoolCallbackImpl = new ThreadPoolCallbackImpl(
            new ThreadLocalWhenInTestAdapterImpl());

    public static boolean start(Object pool, Object task,int methodId) {
        return threadPoolCallbackImpl.start(pool, task, methodId);
    }

    public static void join(Object task, int methodId) {
        threadPoolCallbackImpl.join(task, methodId);
    }

    public static void joinExit() {
           if( isInThreadPool()) {
               setInThreadPool(false);
              // stopProcess();
           }
    }

    // visible for test
    public static void setThreadPoolCallbackImpl(ThreadPoolCallbackImpl threadPoolCallbackImpl) {
        ThreadPoolCallback.threadPoolCallbackImpl = threadPoolCallbackImpl;
    }
}
