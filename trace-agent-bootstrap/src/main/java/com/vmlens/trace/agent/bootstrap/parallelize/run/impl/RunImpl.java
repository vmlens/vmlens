package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings.of;

public class RunImpl implements Run {
    private final ReentrantLock lock;
    private final Condition threadActiveCondition;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;
    private final int loopId;
    private final int runId;

    public RunImpl(ReentrantLock lock, WaitNotifyStrategy waitNotifyStrategy,
                   RunStateMachine runStateMachine, int loopId, int runId) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.lock = lock;
        this.threadActiveCondition = lock.newCondition();
        this.loopId = loopId;
        this.runId = runId;
    }

    public RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        lock.lock();
        try {
            runtimeEvent.setLoopId(loopId);
            runtimeEvent.setRunId(runId);
            RuntimeEvent result = runStateMachine.after(runtimeEvent, threadLocalDataWhenInTest);
            if (runtimeEvent instanceof InterleaveActionFactory) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {

                }
            }
            return of(result);
        } finally {
            lock.unlock();
        }
    }

    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            ThreadLocalWhenInTest threadLocalDataWhenInTest = runStateMachine.processNewTestTask(newWrapper, threadLocalForParallelize, this);
            if (threadLocalDataWhenInTest != null) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {

                }
            }
        } finally {
            lock.unlock();
        }
    }

    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.end(threadLocalForParallelize);
    }

    @Override
    public int runId() {
        return runId;
    }

    @Override
    public void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {

    }

    @Override
    public void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest, RunnableOrThreadWrapper newThread) {

    }
}
