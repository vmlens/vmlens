package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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

    public RuntimeEvent after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        lock.lock();
        try {
            // Fixme send of the event better here?
            RuntimeEvent result = runStateMachine.after(action, threadLocalDataWhenInTest);
            if (result != null) {
                result.setLoopId(loopId);
                result.setRunId(runId);
            }
            try {
                waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
            } catch (TestBlockedException e) {
                runStateMachine.setStateRecording();
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            ThreadLocalDataWhenInTest threadLocalDataWhenInTest = runStateMachine.processNewTestTask(newWrapper, threadLocalForParallelize, this);
            if (threadLocalDataWhenInTest != null) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {
                    runStateMachine.setStateRecording();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.end(threadLocalForParallelize);
    }

}
