package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.StartNewThreadByPool;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public class ThreadPoolCallbackImpl {

    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;


    public ThreadPoolCallbackImpl(ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
            this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        }

        public boolean start(Object pool, Object task, int methodId) {
            return threadLocalWhenInTestAdapter.process(new StartNewThreadByPool(pool,(Runnable) task));
    }

    public void join(Object taskOrPool, int methodId) {
        threadLocalWhenInTestAdapter.join(taskOrPool);
    }
}
