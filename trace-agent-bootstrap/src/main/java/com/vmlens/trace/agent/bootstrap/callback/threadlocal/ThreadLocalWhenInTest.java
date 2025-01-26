package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RuntimeEventAndSetFieldsStrategy;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;

/**
 * Data Structure, only state no behaviour
 *
 */
public class ThreadLocalWhenInTest extends PerThreadCounter implements ThreadLocalWhenInTestForParallelize {
    private final RunAdapter runAdapter;
    private final int threadIndex;


    private RuntimeEventAndSetFieldsStrategy atomicVolatileFieldAccess;
    private ThreadStartEvent threadStartEvent;

    public ThreadLocalWhenInTest(Run run, int threadIndex) {
        this.runAdapter = new RunAdapter(run);
        this.threadIndex = threadIndex;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    public RuntimeEventAndSetFieldsStrategy atomicOperation() {
        return atomicVolatileFieldAccess;
    }

    public void setAtomicVolatileFieldAccess(RuntimeEventAndSetFieldsStrategy atomicVolatileFieldAccess) {
        this.atomicVolatileFieldAccess = atomicVolatileFieldAccess;
    }

    public RunAdapter runAdapter() {
        return runAdapter;
    }

    public ThreadStartEvent threadStartEvent() {
        return threadStartEvent;
    }

    public void setThreadStartEvent(ThreadStartEvent threadStartEvent) {
        this.threadStartEvent = threadStartEvent;
    }
}
