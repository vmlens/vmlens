package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalState;

public class SynchronizedRunAdapter implements Run {

    private final Object LOCK = new Object();
    private final RunStateMachine runStateMachine;

    public SynchronizedRunAdapter(RunStateMachine runStateMachine, ThreadLocalState threadLocalState) {
        this.runStateMachine = runStateMachine;
        threadLocalState.createNewParallelizedThreadLocal(this);
    }

    @Override
    public void after(ParallelizeAction action) {
        synchronized (LOCK) {
            runStateMachine.after(action);
            notifyAndWaitTillActive();
        }
    }

    private void notifyAndWaitTillActive() {
        try {
            LOCK.notifyAll();
            long threadId = Thread.currentThread().getId();
            while (!runStateMachine.isActive(threadId)) {
                LOCK.wait(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void end(ThreadLocalState threadLocalState) {
        threadLocalState.setParallelizedThreadLocalToNull();
        runStateMachine.end();
    }

    @Override
    public void newThread(Thread newThread, ThreadLocalState threadLocalState) {
        synchronized (LOCK) {
            if (runStateMachine.newThread(newThread))
                threadLocalState.createNewParallelizedThreadLocal(this);
            notifyAndWaitTillActive();
        }
    }
}
