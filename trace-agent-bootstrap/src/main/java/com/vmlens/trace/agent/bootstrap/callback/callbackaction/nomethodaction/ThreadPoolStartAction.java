package com.vmlens.trace.agent.bootstrap.callback.callbackaction.nomethodaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.StartNewThreadByPool;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolStartAction extends NoMethodAction {

    private final Object pool;
    private final Runnable task;
    private final AtomicInteger threadCount;

    public ThreadPoolStartAction(Object pool, Runnable task, AtomicInteger threadCount) {
        this.pool = pool;
        this.task = task;

        this.threadCount = threadCount;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        inTestActionProcessor.process(new StartNewThreadByPool(pool,task,threadCount.getAndIncrement()));
    }
}
