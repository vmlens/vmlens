package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

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


    public RunImpl(ReentrantLock lock, WaitNotifyStrategy waitNotifyStrategy,
                   RunStateMachine runStateMachine) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.lock = lock;
        this.threadActiveCondition = lock.newCondition();

    }

    public void after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        lock.lock();
        try {
            runStateMachine.after(action, threadLocalDataWhenInTest);
            try {
                waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
            } catch (TestBlockedException e) {
                runStateMachine.setStateRecording();
            }
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
