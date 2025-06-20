package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.StartNewThreadByPool;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolCallbackImpl {

    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final AtomicInteger threadCount = new AtomicInteger();

    public ThreadPoolCallbackImpl(ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
            this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        }

        public boolean start(Object pool, Object task, int methodId) {
            return threadLocalWhenInTestAdapter.process(new StartNewThreadByPool(pool,
                    (Runnable) task,
                    threadCount.getAndIncrement()));
    }

    public void join(Object taskOrPool, int methodId) {
        threadLocalWhenInTestAdapter.join(taskOrPool);
    }
}
