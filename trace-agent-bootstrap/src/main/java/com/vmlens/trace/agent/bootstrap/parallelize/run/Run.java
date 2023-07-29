package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Run {
    private final ReentrantLock lock;
    private final Condition threadActiveCondition;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;
    private final int id;
    private int maxThreadIndex;

    public Run(ReentrantLock lock, int id, WaitNotifyStrategy waitNotifyStrategy, RunStateMachine runStateMachine, TestThreadState testThreadState) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.id = id;
        this.lock = lock;
        this.threadActiveCondition = lock.newCondition();
        testThreadState.createNewParallelizedThreadLocal(this, maxThreadIndex);
        maxThreadIndex++;
    }

    public void after(ParallelizeAction action, TestThreadState testThreadState) {
        lock.lock();
        try {
            runStateMachine.after(action, testThreadState);
            try {
                waitNotifyStrategy.notifyAndWaitTillActive(testThreadState, runStateMachine, threadActiveCondition);
            } catch (TestBlockedException e) {
                runStateMachine.setStateRecording();
            }
        } finally {
            lock.unlock();
        }
    }
    public void end(TestThreadState testThreadState) {
        testThreadState.setParallelizedThreadLocalToNull();
        runStateMachine.end();
    }
    public void newTask(RunnableOrThreadWrapper newWrapper, TestThreadState testThreadState) {
        lock.lock();
        try {
            if (runStateMachine.isNewTestTask(newWrapper)) {
                testThreadState.createNewParallelizedThreadLocal(this, maxThreadIndex);
                runStateMachine.processNewTestTask(testThreadState);
                maxThreadIndex++;
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(testThreadState, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {
                    runStateMachine.setStateRecording();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // Visible for Test
    public boolean isActive(TestThreadState testThreadState) {
        return runStateMachine.isActive(testThreadState);
    }

    // Visible for Test
    public ActualRun actualRun() {
        return runStateMachine.actualRun();
    }
}
