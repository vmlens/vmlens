package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.StartNewThreadByPool;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

import java.util.concurrent.atomic.AtomicInteger;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;
import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.stopProcess;

public class ThreadPoolCallbackImpl {

    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final AtomicInteger threadCount = new AtomicInteger();

    public ThreadPoolCallbackImpl(ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
            this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public boolean start(Object pool, Object task, int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                return threadLocalWhenInTestAdapter.process(new StartNewThreadByPool(pool,
                        (Runnable) task,
                        threadCount.getAndIncrement()));
            } finally {
                stopProcess();
            }
        }
        return false;
    }

    public void join(Object taskOrPool, int methodId) {
        if(canProcess()) {
            startProcess();
            setInThreadPool(true);
            threadLocalWhenInTestAdapter.join(taskOrPool);
            stopProcess();
        }

    }
}
