package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.command.ParallelizeCommand;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRun;

public class ParallelizedThreadLocal {


    private final SynchronizationWrapperForRun synchronizationWrapperForRun;
    private ParallelizeCommand currentFieldAccess = null;

    public ParallelizedThreadLocal(SynchronizationWrapperForRun synchronizationWrapperForRun) {
        this.synchronizationWrapperForRun = synchronizationWrapperForRun;
    }

    void before(ParallelizeCommand wrapper) {
        currentFieldAccess = wrapper;
    }

    public void afterFieldAccess() {
        synchronizationWrapperForRun.after(currentFieldAccess);
        currentFieldAccess = null;
    }

    void after() {

    }

}
